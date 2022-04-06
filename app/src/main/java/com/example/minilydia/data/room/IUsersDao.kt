package com.example.minilydia.data.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.minilydia.data.entity.local.UserRoomEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface IUsersDao {

    @Query("SELECT COUNT() FROM random_users")
    fun getUsersCount(): Single<Int>

    @Query("SELECT * FROM random_users")
    fun getUsers(): DataSource.Factory<Int, UserRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserRoomEntity>): Completable

    @Query("DELETE FROM random_users")
    fun deleteAllUsers(): Completable
}