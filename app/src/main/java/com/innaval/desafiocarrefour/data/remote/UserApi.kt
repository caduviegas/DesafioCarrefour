package com.innaval.desafiocarrefour.data.remote

import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getAllUsers(): Response<List<UserModel>>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String?,
    ): Response<UserModel>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
    ): Response<List<UserRepositoryModel>>
}