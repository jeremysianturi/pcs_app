package com.pcs.domain.usecase

import com.pcs.domain.repository.UserRepository
import com.pcs.domain.utils.ApiUseCaseNonParams
import com.pcs.domain.utils.ApiUseCaseParams
import com.pcs.domain.utils.Result
import com.pcs.entity.UserItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListUseCase @Inject constructor(
    private val repository: UserRepository
): ApiUseCaseNonParams<List<UserItemEntity>> {
    override suspend fun execute(): Flow<Result<List<UserItemEntity>>> {
        return repository.fetchUserList()
    }
    data class Params(val name: String)
}