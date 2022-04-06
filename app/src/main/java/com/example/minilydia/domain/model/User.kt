package com.example.minilydia.domain.model

import java.io.Serializable

data class User(
    val id: String,
    val firstName: String,
    val surname: String,
    val gender: String,
    val email: String,
    val smallPicture: String,
    val normalPicture: String,
    val largePicture: String,
    val phone: String,
    val city: String,
    val registered: Long
): Serializable {
    fun getFullName(): String {
        return "${this.firstName} ${this.surname}"
    }
}