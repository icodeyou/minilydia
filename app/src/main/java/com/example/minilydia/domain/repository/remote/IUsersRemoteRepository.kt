package com.example.minilydia.domain.repository.remote

import com.example.minilydia.domain.model.User
import io.reactivex.Single

interface IUsersRemoteRepository {
    fun getUsersFromApi(page: Int): Single<List<User>>
}