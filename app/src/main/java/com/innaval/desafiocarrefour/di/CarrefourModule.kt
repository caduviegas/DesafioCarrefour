package com.innaval.desafiocarrefour.di

import com.innaval.desafiocarrefour.data.remote.UserApi
import com.innaval.desafiocarrefour.data.repository.UserRepositoryImplementation
import com.innaval.desafiocarrefour.domain.repository.UserRepository
import com.innaval.desafiocarrefour.domain.usecase.AllUsersUseCase
import com.innaval.desafiocarrefour.domain.usecase.AllUsersUseCaseImplementation
import com.innaval.desafiocarrefour.domain.usecase.UserRepositoryUseCase
import com.innaval.desafiocarrefour.domain.usecase.UserRepositoryUseCaseImplementation
import com.innaval.desafiocarrefour.domain.usecase.UserUseCase
import com.innaval.desafiocarrefour.domain.usecase.UserUseCaseImplementation
import com.innaval.desafiocarrefour.presentation.user.detail.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun allModules() = listOf(
    apiModule,
    repositoriesModule,
    useCasesModule,
    viewModelsModule,
)

private val apiModule = module {
    single {
        provideUserApi()
    }
}

private fun provideUserApi(): UserApi {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(UserApi::class.java)
}

private val useCasesModule = module {
    single<AllUsersUseCase> { AllUsersUseCaseImplementation(get()) }
    single<UserUseCase> { UserUseCaseImplementation(get()) }
    single<UserRepositoryUseCase> { UserRepositoryUseCaseImplementation(get()) }
}

private val repositoriesModule = module {
    single<UserRepository> { UserRepositoryImplementation(get()) }
}

private val viewModelsModule = module {

    viewModel { UserDetailsViewModel(get(), get()) }
}
