package com.pcs.data.mapper

import com.pcs.apiresponse.UserItemApiResponse
import com.pcs.data.utils.Mapper
import com.pcs.entity.UserItemEntity
import javax.inject.Inject

class UserListItemMapper @Inject constructor() : Mapper<List<UserItemApiResponse>,List<UserItemEntity>>{
    override fun mapFromApiResponse(type: List<UserItemApiResponse>): List<UserItemEntity> {
        return type.map {
            UserItemEntity(
                createdAt = it.createdAt ?: "No Date Found",
                name = it.name ?: "No Name Found",
                avatar = it.avatar ?: "https://ibb.co.com/TWwBvjJ",
                city = it.city ?: "No City Found",
                country = it.country ?: "No Country Found",
                county = it.county ?: "Not County Found",
                addressNo = it.address_no ?: "No Address Found",
                street = it.street ?: "No Street Found",
                zipCode = it.zip_code ?: "No Zip Code Found",
                id = it.id ?: "No Id Found"
            )
        }
    }
}