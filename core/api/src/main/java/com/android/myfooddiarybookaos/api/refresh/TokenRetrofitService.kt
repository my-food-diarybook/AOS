package com.android.myfooddiarybookaos.api.refresh

import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.http.POST

interface TokenRetrofitService {

    @POST("user/refresh-token")
    suspend fun resetToken(): LoginResponse

}
