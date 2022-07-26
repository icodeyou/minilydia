package com.example.minilydia.data.repository.remote

import com.example.minilydia.data.api.IUsersApi
import com.example.minilydia.data.mapper.mapRemoteUserToDomain
import com.example.minilydia.domain.model.User
import com.example.minilydia.domain.repository.remote.IUsersRemoteRepository
import io.reactivex.Single

class UsersRemoteRepositoryImpl(private val usersApi: IUsersApi): IUsersRemoteRepository {

    override fun getUsersFromApi(page: Int): Single<List<User>> = usersApi.getUsers(page)
        .map { response ->
            response.results.map(mapRemoteUserToDomain)
        }
}