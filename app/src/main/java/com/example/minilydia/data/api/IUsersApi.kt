package com.example.minilydia.data.api

import com.example.minilydia.data.entity.remote.ResponseEntity
import com.example.minilydia.data.DataConstants.DEFAULT_PAGE
import com.example.minilydia.data.DataConstants.DEFAULT_PAGE_SIZE
import com.example.minilydia.data.DataConstants.DEFAULT_SEED
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IUsersApi {

    @GET(" ")
    fun getUsers(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("results") results: Int = DEFAULT_PAGE_SIZE,
        @Query("seed") seed: String = DEFAULT_SEED
    ): Single<ResponseEntity>
}