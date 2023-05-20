package com.innaval.desafiocarrefour.presentation.user.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innaval.desafiocarrefour.core.Status
import com.innaval.desafiocarrefour.core.extensions.read
import com.innaval.desafiocarrefour.core.extensions.toJsonString
import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel
import com.innaval.desafiocarrefour.domain.usecase.UserRepositoryUseCase
import com.innaval.desafiocarrefour.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserDetailsViewModel(
    private val userUseCase: UserUseCase,
    private val userReposUseCase: UserRepositoryUseCase,
) : ViewModel() {

    private val _loadingStatus: MutableLiveData<Status> = MutableLiveData()
    val loadingStatus: LiveData<Status> = _loadingStatus

    private val _userModel = MutableLiveData<UserModel?>()
    val userModel: LiveData<UserModel?> = _userModel

    private val _userRepos = MutableLiveData<List<UserRepositoryModel>>()
    val userRepos: LiveData<List<UserRepositoryModel>> = _userRepos

    fun getUser(login: String) = viewModelScope.launch {
        _loadingStatus.value = Status.LOADING
        withContext(Dispatchers.IO) {
            userUseCase(login)
        }.read(
            {
                Timber.tag("UserDetailsViewModel/getUserModel").d("Success: ${it.toJsonString()}")
                _userModel.value = (it)
                _loadingStatus.value = Status.SUCCESS
                getUserRepos(login = it.login)
            },
            {
                Timber.tag("UserDetailsViewModel/getUserModel").e("Error: ${it.message}")
                _loadingStatus.value = Status.ERROR
            },
        )
    }

    fun getUserRepos(login: String) = viewModelScope.launch {
        _loadingStatus.value = Status.LOADING
        withContext(Dispatchers.IO) {
            userReposUseCase(login)
        }.read(
            {
                Timber.tag("UserDetailsViewModel/getUserRepos").d("Success: ${it.toJsonString()}")
                _userRepos.value = (it)
                _loadingStatus.value = Status.SUCCESS
            },
            {
                Timber.tag("UserDetailsViewModel/getUserRepos").e("Error: ${it.message}")
                _loadingStatus.value = Status.ERROR
            },
        )
    }
}
