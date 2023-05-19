package com.innaval.desafiocarrefour.domain.usecase

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.data.remote.ResponseEmpty
import com.innaval.desafiocarrefour.data.remote.ResponseError
import com.innaval.desafiocarrefour.data.remote.ResponseSuccess
import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.repository.UserRepository

class AllUsersUseCaseImplementation(
    private val userRepository: UserRepository,
) : AllUsersUseCase {

    override suspend fun invoke(): ResponseAny<List<UserModel>> {
        return when (val result = userRepository.getAllUsers()) {
            is ResponseSuccess -> result
            is ResponseEmpty -> result
            is ResponseError -> result
        }
    }
}