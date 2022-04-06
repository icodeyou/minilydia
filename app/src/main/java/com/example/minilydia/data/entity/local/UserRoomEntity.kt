package com.example.minilydia.data.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "random_users")
data class UserRoomEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
    val gender: String,
    val email: String,
    val smallPicture: String,
    val normalPicture: String,
    val largePicture: String,
    val phone: String,
    val city: String,
    val registered: Long,
)