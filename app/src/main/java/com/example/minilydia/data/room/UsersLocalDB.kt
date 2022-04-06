package com.example.minilydia.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minilydia.data.DataConstants.DATABASE_VERSION
import com.example.minilydia.data.entity.local.UserRoomEntity

@Database(entities = [UserRoomEntity::class], version = DATABASE_VERSION)
abstract class UsersLocalDB: RoomDatabase() {
    abstract fun usersDao(): IUsersDao
}