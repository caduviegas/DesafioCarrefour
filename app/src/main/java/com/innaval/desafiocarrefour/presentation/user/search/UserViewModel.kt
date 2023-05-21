package com.innaval.desafiocarrefour.presentation.user.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innaval.desafiocarrefour.core.Status
import com.innaval.desafiocarrefour.core.extensions.read
import com.innaval.desafiocarrefour.core.extensions.toJsonString
import com.innaval.desafiocarrefour.domain.model.UserModel
import com.innaval.desafiocarrefour.domain.usecase.AllUsersUseCase
import com.innaval.desafiocarrefour.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserViewModel (
    private val allUsersUseCase: AllUsersUseCase,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _loadingStatus: MutableLiveData<Status> = MutableLiveData()
    val loadingStatus: LiveData<Status> = _loadingStatus

    private val _allUsers = MutableLiveData<List<UserModel>>()
    val allUsers: LiveData<List<UserModel>> = _allUsers

    private val _userModel = MutableLiveData<UserModel?>()
    val userModel: LiveData<UserModel?> = _userModel

    init {
        getAllUsers()
    }

    fun getAllUsers() = viewModelScope.launch {
        _loadingStatus.value = (Status.LOADING)
        withContext(Dispatchers.IO) {
            allUsersUseCase()
        }.read(
            {
                Timber.tag("UserSearchViewModel/getAllUsers").d("Success: ${it.toJsonString()}")
                _loadingStatus.value = (Status.SUCCESS)
                _allUsers.value = (it)
            },
            {
                Timber.tag("UserSearchViewModel/getAllUsers").e("Error: ${it.message}")
                _loadingStatus.value = (Status.ERROR)
            },
        )
    }

    fun getUser(name: String?) = viewModelScope.launch {
        _loadingStatus.value = (Status.LOADING)
        withContext(Dispatchers.IO) {
            userUseCase(name)
        }.read(
            {
                Timber.tag("UserSearchViewModel/getUserModel").d("Success: ${it.toJsonString()}")
                _loadingStatus.value = (Status.SUCCESS)
                _userModel.value = (it)
            },
            {
                Timber.tag("UserSearchViewModel/getUserModel").e("Error: ${it.message}")
                _loadingStatus.value = (Status.ERROR)
            },
        )
    }
}
