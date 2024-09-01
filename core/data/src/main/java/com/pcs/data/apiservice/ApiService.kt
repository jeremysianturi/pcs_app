package com.pcs.data.apiservice

import com.pcs.apiresponse.ProfileApiResponse
import com.pcs.apiresponse.UserItemApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("getData/test")
    suspend fun fetchUserList(): Response<List<UserItemApiResponse>>

    @GET("/users/{username}")
    suspend fun fetchProfile(
        @Path("username")username:String
    ):Response<ProfileApiResponse>
}