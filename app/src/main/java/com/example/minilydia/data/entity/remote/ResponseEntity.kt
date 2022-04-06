package com.example.minilydia.data.entity.remote

data class ResponseEntity(
    val results: List<UserEntity>,
    val info: InfoEntity
)