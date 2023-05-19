package com.innaval.desafiocarrefour.domain.usecase

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel

interface UserRepositoryUseCase {

    suspend operator fun invoke(name: String): ResponseAny<List<UserRepositoryModel>>
}