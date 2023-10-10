package com.android.myfooddiarybookaos.api.userApi

import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserRetrofitService {

    @POST("user/login")
    fun userLogin(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<LoginResponse>


    @POST("user/new")
    fun createUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<CreateUserResponse>
}