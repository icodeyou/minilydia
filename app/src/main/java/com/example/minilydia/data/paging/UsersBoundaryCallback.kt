package com.example.minilydia.data.paging

import androidx.paging.PagedList
import com.example.minilydia.data.DataConstants.DEFAULT_PAGE_SIZE
import com.example.minilydia.data.entity.local.UserRoomEntity
import com.example.minilydia.data.mapper.mapDomainUserToLocal
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.local.IUsersLocalRepository
import com.example.minilydia.domain.repository.remote.IUsersRemoteRepository
import com.example.minilydia.ui.common.showLong
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.net.UnknownHostException

class UsersBoundaryCallback(
    private val iUsersLocalRepository: IUsersLocalRepository,
    private val iUsersRemoteRepository: IUsersRemoteRepository
) : PagedList.BoundaryCallback<User>() {

    private var nextPage = 1
    private var isRequestRunning = false
    private val disposables = CompositeDisposable()

    override fun onZeroItemsLoaded() {
        Timber.i("onZeroItemsLoaded")
        getUsersFromApiAndSaveInLocalDB()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        Timber.i("onItemAtEndLoaded : ${itemAtEnd.getFullName()}")
        getUsersFromApiAndSaveInLocalDB()
    }

    private fun getUsersFromApiAndSaveInLocalDB() {
        if (isRequestRunning) return

        isRequestRunning = true
        iUsersLocalRepository.getUsersCountFromLocalDB()
            .flatMap { usersCount -> getUsersFromApi(usersCount) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doFinally { isRequestRunning = false }
            .subscribe(
                { Timber.i("$nextPage page retrieved from API and users saved in database successfully") },
                {
                    Timber.e("Error retrieving data in UsersBoundaryCallback : \n ${it.stackTrace}")
                    if (it is UnknownHostException) {
                        Timber.e("Check the internet connection")
                    }
                }
            ).addTo(disposables)
    }

    private fun getUsersFromApi(usersCount: Int): Single<List<UserRoomEntity>> {
        nextPage = (usersCount / DEFAULT_PAGE_SIZE) + 1

        return iUsersRemoteRepository.getUsersFromApi(nextPage)
            .map { users -> users.map(mapDomainUserToLocal) }
            .doOnSuccess { users ->
                saveUsersInDatabase(users)
                Timber.d("DEBUG - Success getting ${users.count()} new users.")
            }
            .doOnError {
                Timber.d("DEBUG - Error getting users")
                Timber.e(it)
            }
    }


    private fun saveUsersInDatabase(users: List<UserRoomEntity>) {
        if (users.isNotEmpty()) {
            iUsersLocalRepository.saveUsersInLocalDB(users)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnComplete {
                    Timber.i("${users.size} users saved in database")
                }
                .subscribe()
                .addTo(disposables)
        }
    }
}