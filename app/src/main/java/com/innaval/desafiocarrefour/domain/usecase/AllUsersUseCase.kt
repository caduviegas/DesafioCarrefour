package com.innaval.desafiocarrefour.domain.usecase

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.domain.model.UserModel

interface AllUsersUseCase {

    suspend operator fun invoke(): ResponseAny<List<UserModel>>
}