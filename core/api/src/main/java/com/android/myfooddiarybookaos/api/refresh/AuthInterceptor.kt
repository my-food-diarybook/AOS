package com.android.myfooddiarybookaos.api.refresh

import android.content.Context
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.api.userApi.UserRetrofitService
import com.android.myfooddiarybookaos.model.login.LoginResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val context: Context,
    private val tokenApi: TokenRetrofitService,
): Interceptor,BaseRepository(){
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code){
            500 -> {
                return runBlocking {
                    val userData =  UserInfoSharedPreferences(context)
                    when(val token = getUpdateToken() ){
                        is Resource.Success -> {
                            userData.accessToken = token.value.token
                            userData.refreshToken = token.value.refreshToken

                            val newRequest = chain.request().newBuilder().removeHeader("token")
                            userData.accessToken?.let { newRequest.addHeader("token", it) }
                            return@runBlocking chain.proceed(newRequest.build())
                        }
                        else ->{
                            return@runBlocking response
                        }
                    }
                }
            }
        }

        return response
    }

    private suspend fun getUpdateToken() : Resource<LoginResponse>{
        return safeApiCall {
            tokenApi.resetToken()
        }
    }
}