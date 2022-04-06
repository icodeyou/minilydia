package com.example.minilydia.domain.repository

import androidx.paging.PagedList
import com.example.minilydia.domain.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface IUsersRepository {
    fun getUserList(): Observable<PagedList<User>>
    fun deleteLocalUsers(): Completable
}