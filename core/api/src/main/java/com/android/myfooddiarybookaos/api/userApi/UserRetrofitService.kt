package com.android.myfooddiarybookaos.api.userApi

import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserRetrofitService {

    @POST("user/login")
    fun userLogin(
        @Query("email") email : String,
        @Query("password") password : String
    ) : LoginResponse


    @POST("user/new")
    fun createUser(
        @Query("email") email : String,
        @Query("password") password : String
    ) : CreateUserResponse
}