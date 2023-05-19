package com.innaval.desafiocarrefour.domain.usecase

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.domain.model.UserModel

interface UserUseCase {
    suspend operator fun invoke(login: String?): ResponseAny<UserModel>
}