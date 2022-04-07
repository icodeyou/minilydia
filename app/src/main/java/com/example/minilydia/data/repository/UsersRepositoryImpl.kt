package com.example.minilydia.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.IUsersRepository
import com.example.minilydia.domain.repository.local.IUsersLocalRepository
import io.reactivex.Completable
import io.reactivex.Observable

class UsersRepositoryImpl(
    private val usersLocalRepository: IUsersLocalRepository,
    private val pagedListBuilder: RxPagedListBuilder<Int, User>
): IUsersRepository {

    override fun getUserList(): Observable<PagedList<User>> = pagedListBuilder.buildObservable()

    override fun deleteLocalUsers(): Completable = usersLocalRepository.deleteUsersFromLocalDB()
}