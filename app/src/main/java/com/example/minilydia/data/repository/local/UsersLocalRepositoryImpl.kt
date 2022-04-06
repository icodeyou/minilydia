package com.example.minilydia.data.repository.local

import androidx.paging.DataSource
import com.example.minilydia.data.entity.local.UserRoomEntity
import com.example.minilydia.data.mapper.mapLocalUserToDomain
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.local.IUsersLocalRepository
import com.example.minilydia.data.room.UsersLocalDB
import io.reactivex.Completable
import io.reactivex.Single

class UsersLocalRepositoryImpl(private val usersLocalDB: UsersLocalDB): IUsersLocalRepository {

    override fun getUsersCountFromLocalDB(): Single<Int> = usersLocalDB.usersDao().getUsersCount()

    override fun getUsersFromLocalDB(): DataSource.Factory<Int, User> = usersLocalDB.usersDao().getUsers()
        .mapByPage { users -> users.map(mapLocalUserToDomain) }

    override fun saveUsersInLocalDB(users: List<UserRoomEntity>): Completable = usersLocalDB.usersDao().insertUsers(users)

    override fun deleteUsersFromLocalDB(): Completable = usersLocalDB.usersDao().deleteAllUsers()
}