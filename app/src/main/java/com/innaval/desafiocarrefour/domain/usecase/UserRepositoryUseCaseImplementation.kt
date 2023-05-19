package com.innaval.desafiocarrefour.domain.usecase

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.data.remote.ResponseEmpty
import com.innaval.desafiocarrefour.data.remote.ResponseError
import com.innaval.desafiocarrefour.data.remote.ResponseSuccess
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel
import com.innaval.desafiocarrefour.domain.repository.UserRepository

class UserRepositoryUseCaseImplementation (
    private val userRepository: UserRepository,
) : UserRepositoryUseCase {

    override suspend fun invoke(name: String): ResponseAny<List<UserRepositoryModel>> {
        return when (val result = userRepository.getUserRepos(name)) {
            is ResponseSuccess -> result
            is ResponseEmpty -> result
            is ResponseError -> result
        }
    }
}