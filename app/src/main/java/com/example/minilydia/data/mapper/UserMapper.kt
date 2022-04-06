package com.example.minilydia.data.mapper

import com.example.minilydia.data.entity.local.UserRoomEntity
import com.example.minilydia.data.entity.remote.UserEntity
import com.example.minilydia.domain.model.User

val mapRemoteUserToDomain: (UserEntity) -> User = { user ->
    User(
        user.login?.sha256 ?: "",
        user.name?.first ?: "",
        user.name?.last ?: "",
        user.gender ?: "",
        user.email ?: "",
        user.picture?.thumbnail ?: "",
        user.picture?.medium ?: "",
        user.picture?.large ?: "",
        user.phone ?: "",
        user.location?.city ?: "",
        user.registered ?: -1)
}

val mapLocalUserToDomain: (UserRoomEntity) -> User = { user ->
    User(
        user.id,
        user.name,
        user.surname,
        user.gender,
        user.email,
        user.smallPicture,
        user.normalPicture,
        user.largePicture,
        user.phone,
        user.city,
        user.registered)
}

val mapDomainUserToLocal: (User) -> UserRoomEntity = { user ->
    UserRoomEntity(
        user.id,
        user.firstName,
        user.surname,
        user.gender,
        user.email,
        user.smallPicture,
        user.normalPicture,
        user.largePicture,
        user.phone,
        user.city,
        user.registered)
}