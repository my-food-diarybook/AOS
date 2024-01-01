package com.android.myfooddiarybookaos.api.userApi

import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import com.android.myfooddiarybookaos.model.login.UserRequest
import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface UserRetrofitService {

    @POST("user/login")
    fun userLogin(
        @Body userRequest: UserRequest
    ): Call<LoginResponse>


    @POST("user/new")
    fun createUser(
        @Body userRequest: UserRequest
    ): Call<CreateUserResponse>

    @GET("oauth/google")
    suspend fun loginGoogle(
        @Query("idToken") idToken: String
    )
}