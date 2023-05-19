package com.innaval.desafiocarrefour.data.repository

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.data.remote.UserApi
import com.innaval.desafiocarrefour.data.remote.safeApiCall
import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel
import com.innaval.desafiocarrefour.domain.repository.UserRepository

class UserRepositoryImplementation (
    private val userApi: UserApi,
) : UserRepository {

    override suspend fun getAllUsers() = safeApiCall { userApi.getAllUsers() }

    override suspend fun getUser(login: String?): ResponseAny<UserModel> = safeApiCall { userApi.getUser(login) }

    override suspend fun getUserRepos(login: String): ResponseAny<List<UserRepositoryModel>> = safeApiCall { userApi.getUserRepos(login) }
}