package com.example.minilydia.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.IUsersRepository
import io.reactivex.Flowable
import timber.log.Timber

class UsersViewModel(private val iUsersRepository: IUsersRepository): ViewModel() {

    private val _users: MutableLiveData<PagingData<User>> = MutableLiveData()
    val users: LiveData<PagingData<User>>
        get() = _users

    fun getUsers() {
        Timber.i("Executing GetUserListUseCase...")
        iUsersRepository.getUserList() // TODO : Handle errors
    }
}