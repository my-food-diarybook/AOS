package com.android.myfooddiarybookaos.api.userApi

import com.android.myfooddiarybookaos.model.login.*
import com.android.myfooddiarybookaos.model.token.UserToken
import retrofit2.Call
import retrofit2.Response
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

    @GET("user/google-login-callback")
    suspend fun loginGoogle(
        @Query("code") code: String
    ): Response<Unit>

    @POST("user/reset-password")
    @FormUrlEncoded
    suspend fun resetUserPassword(
        @Field("email") email : String,
    ): Response<ResetPassState>
}