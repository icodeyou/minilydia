package com.example.minilydia.data.paging

import androidx.paging.PagedList
import com.example.minilydia.data.DataConstants.DEFAULT_PAGE_SIZE
import com.example.minilydia.data.entity.local.UserRoomEntity
import com.example.minilydia.data.mapper.mapDomainUserToLocal
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.local.IUsersLocalRepository
import com.example.minilydia.domain.repository.remote.IUsersRemoteRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UsersBoundaryCallback(
    private val iUsersLocalRepository: IUsersLocalRepository,
    private val iUsersRemoteRepository: IUsersRemoteRepository
): PagedList.BoundaryCallback<User>() {

    private var nextPage = 1
    private var isRequestRunning = false
    private val disposables = CompositeDisposable()

    override fun onZeroItemsLoaded() {
        Timber.i("onZeroItemsLoaded")
        getUsersFromApiAndSaveInDatabase()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        Timber.i("onItemAtEndLoaded")
        getUsersFromApiAndSaveInDatabase()
    }

    private fun getUsersFromApiAndSaveInDatabase() {
        if(isRequestRunning) return

        isRequestRunning = true
        val disposable = iUsersLocalRepository.getUsersCountFromLocalDB()
            .flatMap { usersCount -> getUsersFromApi(usersCount) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doFinally { isRequestRunning = false }
            .subscribe(
                { Timber.i("$nextPage page retrieved from API and users saved in database successfully") },
                { it.printStackTrace() }
            )
        disposables.add(disposable)
    }

    private fun getUsersFromApi(usersCount: Int): Single<List<UserRoomEntity>> {
        nextPage = (usersCount / DEFAULT_PAGE_SIZE) + 1

        return iUsersRemoteRepository.getUsersFromApi(nextPage)
            .map { users -> users.map(mapDomainUserToLocal) }
            .doOnSuccess { users -> saveUsersInDatabase(users) }
    }

    private fun saveUsersInDatabase(users: List<UserRoomEntity>) {
        if(users.isNotEmpty()) {
            val disposable = iUsersLocalRepository.saveUsersInLocalDB(users)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnComplete {
                    Timber.i("${users.size} users saved in database")
                }
                .subscribe()
            disposables.add(disposable)
        }
    }
}