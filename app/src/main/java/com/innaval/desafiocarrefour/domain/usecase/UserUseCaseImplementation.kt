package com.innaval.desafiocarrefour.domain.usecase

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.data.remote.ResponseEmpty
import com.innaval.desafiocarrefour.data.remote.ResponseError
import com.innaval.desafiocarrefour.data.remote.ResponseSuccess
import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.repository.UserRepository

class UserUseCaseImplementation(
    private val userRepository: UserRepository,
) : UserUseCase {

    override suspend fun invoke(login: String?): ResponseAny<UserModel> {
        return when (val result = userRepository.getUser(login)) {
            is ResponseSuccess -> result
            is ResponseEmpty -> result
            is ResponseError -> result
        }
    }
}