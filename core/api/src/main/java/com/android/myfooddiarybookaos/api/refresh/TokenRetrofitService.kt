package com.android.myfooddiarybookaos.api.refresh

import com.android.myfooddiarybookaos.model.login.LoginResponse
import com.android.myfooddiarybookaos.model.token.UserToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TokenRetrofitService {

    @POST("user/refresh-token")
    suspend fun resetToken(): LoginResponse

}