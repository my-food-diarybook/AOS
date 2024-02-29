package com.android.myfooddiarybookaos.api.userApi

import com.android.myfooddiarybookaos.model.login.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserRetrofitService {

    @POST("user/login")
    suspend fun userLogin(
        @Body userRequest: UserRequest
    ): Response<LoginResponse>


    @POST("user/new")
    fun createUser(
        @Body userRequest: UserRequest
    ): Call<CreateUserResponse>

    @GET("user/google-login-callback")
    suspend fun loginGoogle(
        @Query("code") code: String
    ): Response<Unit>

    @POST("user/reset-password")
    suspend fun resetUserPassword(
        @Body passwordResetRequest: PasswordResetRequest,
    ): Response<ResetPassState>
}
