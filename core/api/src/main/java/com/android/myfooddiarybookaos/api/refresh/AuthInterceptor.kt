package com.android.myfooddiarybookaos.api.refresh

import android.content.Context
import android.util.Log
import com.android.myfooddiarybookaos.api.BASE_URL
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.api.userApi.UserRetrofitService
import com.android.myfooddiarybookaos.model.login.LoginResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(
    private val context: Context,
    private val loginFom: String,
) : Interceptor, BaseRepository() {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            401 -> {
                return runBlocking {

                    val userData = UserInfoSharedPreferences(context)
                    val tokenApi = getTokenRetrofit(userData.refreshToken,loginFom)
                    when (val token = getUpdateToken(tokenApi)) {
                        is Resource.Success -> {
                            userData.accessToken = token.value.token
                            userData.refreshToken = token.value.refreshToken

                            val newRequest = chain.request().newBuilder().removeHeader("token")
                            userData.accessToken?.let { newRequest.addHeader("token", it) }
                            return@runBlocking chain.proceed(newRequest.build())
                        }
                        else -> {
                            return@runBlocking response
                        }
                    }
                }
            }
        }

        return response
    }

    private suspend fun getUpdateToken(
        tokenApi: TokenRetrofitService
    ): Resource<LoginResponse> {
        return safeApiCall {
            tokenApi.resetToken()
        }
    }

    private fun getTokenRetrofit(
        refresh: String?,
        loginForm: String
    ): TokenRetrofitService {
        //로그 기록 인터셉터 등록
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient
            .Builder().addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    if (refresh != null) {
                        it.addHeader("refresh-token",refresh)
                        it.addHeader("login-from", loginForm)
                        it.addHeader("Content-Type", "application/json")
                    }
                }.build())
            }
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("$BASE_URL/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TokenRetrofitService::class.java)

    }
}