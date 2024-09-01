package com.pcs.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcs.domain.usecase.UserListUseCase
import com.pcs.domain.utils.Result
import com.pcs.entity.UserItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListUseCase: UserListUseCase
): ViewModel(){
    private val _userListUiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val userListUiState get() = _userListUiState.asStateFlow()

    init {
        fetchUserList()
    }

    private fun fetchUserList(){
        viewModelScope.launch {
            userListUseCase.execute().collect{ response->
                when(response){
                    is Result.Error -> _userListUiState.value  = UserListUiState.Error(response.message)
                    is Result.Loading -> _userListUiState.value  = UserListUiState.Loading
                    is Result.Success ->{
                        if (response.data.isEmpty()){
                            _userListUiState.value = UserListUiState.UserListEmpty
                            return@collect
                        }
                        _userListUiState.value = UserListUiState.HasUserList(response.data)
                    }
                }
            }
        }
    }

    fun handleAction(action: UserListUiAction){
        when(action){
            UserListUiAction.FetchUserList -> fetchUserList()
        }
    }
}

sealed interface UserListUiState{
    data object Loading:UserListUiState
    data class HasUserList(val userList:List<UserItemEntity>):UserListUiState
    data object UserListEmpty:UserListUiState
    data class Error(val message:String):UserListUiState
}

sealed interface UserListUiAction{
    data object FetchUserList:UserListUiAction
}