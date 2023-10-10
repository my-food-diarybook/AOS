package com.android.myfooddiarybookaos.api.userApi

import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface UserRetrofitService {

    @POST("user/login")
    @FormUrlEncoded
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @POST("user/new")
    @FormUrlEncoded
    fun createUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): CreateUserResponse
}