package com.example.minilydia.domain.repository.local

import androidx.paging.DataSource
import com.example.minilydia.data.entity.local.UserRoomEntity
import com.example.minilydia.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface IUsersLocalRepository {
    fun getUsersCountFromLocalDB(): Single<Int>
    fun getUsersFromLocalDB(): DataSource.Factory<Int, User>
    fun saveUsersInLocalDB(users: List<UserRoomEntity>): Completable
    fun deleteUsersFromLocalDB(): Completable
}