package com.example.minilydia.data.entity.remote

data class UserEntity(
    val gender: String? = null,
    val name: NameEntity? = null,
    val location: LocationEntity? = null,
    val email: String? = null,
    val login: LoginEntity? = null,
    val dob: Long? = null,
    val registered: Long? = null,
    val phone: String? = null,
    val cell: String? = null,
    val id: IdEntity? = null,
    val picture: PictureEntity? = null,
    val nat: String? = null,
)