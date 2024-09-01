package com.pcs.domain.repository

import com.pcs.domain.usecase.UserListUseCase
import com.pcs.entity.UserItemEntity
import kotlinx.coroutines.flow.Flow
import com.pcs.domain.utils.Result


interface UserRepository {
    suspend fun fetchUserList(): Flow<Result<List<UserItemEntity>>>
}