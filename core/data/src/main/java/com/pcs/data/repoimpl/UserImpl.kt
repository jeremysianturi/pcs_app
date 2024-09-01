package com.pcs.data.repoimpl

import com.pcs.data.apiservice.ApiService
import com.pcs.data.mapper.UserListItemMapper
import com.pcs.data.utils.NetworkBoundResource
import com.pcs.data.utils.mapFromApiResponse
import com.pcs.domain.repository.UserRepository
import com.pcs.domain.usecase.UserListUseCase
import com.pcs.domain.utils.Result
import com.pcs.entity.UserItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkBoundResources: NetworkBoundResource,
    private val userListItemMapper: UserListItemMapper,
):UserRepository{

    override suspend fun fetchUserList(): Flow<Result<List<UserItemEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                apiService.fetchUserList()
            },userListItemMapper
        )
    }

}