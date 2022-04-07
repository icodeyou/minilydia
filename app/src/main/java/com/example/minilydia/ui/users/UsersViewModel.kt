package com.example.minilydia.ui.users

import com.example.minilydia.ui.common.utils.Resource
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.IUsersRepository
import com.example.minilydia.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UsersViewModel(private val iUsersRepository: IUsersRepository) : BaseViewModel() {

    private val _users: MutableLiveData<Resource<PagedList<User>>> = MutableLiveData()
    val users: MutableLiveData<Resource<PagedList<User>>>
        get() = _users

    fun getUsers() {
        Timber.i("Executing GetUserListUseCase...")
        iUsersRepository.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Successfully retrieve data in ViewModel")
                    _users.postValue(Resource.Success(it))
                },
                {
                    Timber.e("Error retrieving data in ViewModel")
                    Timber.e(it)
                    _users.postValue(Resource.Error(it))
                }).addTo(disposables)
    }
}