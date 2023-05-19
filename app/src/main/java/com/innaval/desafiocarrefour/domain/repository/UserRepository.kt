package com.innaval.desafiocarrefour.domain.repository

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel

interface UserRepository {

    suspend fun getAllUsers(): ResponseAny<List<UserModel>>

    suspend fun getUser(login: String?): ResponseAny<UserModel>

    suspend fun getUserRepos(login: String): ResponseAny<List<UserRepositoryModel>>
}