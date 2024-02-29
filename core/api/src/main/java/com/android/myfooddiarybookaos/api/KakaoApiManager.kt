package com.android.myfooddiarybookaos.api

import android.content.Context
import com.android.myfooddiarybookaos.api.kakao.KakaoRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KakaoApiManager(
    private val context: Context
) {
    private fun makeLoginRetrofit(): Retrofit {
        val userToken = UserInfoSharedPreferences(context).accessToken ?: ""
        return Retrofit.Builder()
            .baseUrl(KAKAO_LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .header("Authorization", "Bearer $userToken")
                            .build()
                        chain.proceed(request)
                    }
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            this.level = HttpLoggingInterceptor.Level.BODY
                        }
                    ).build()
            )
            .build()
    }

    companion object {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getKakaoMapService(): KakaoRetrofitService =
        retrofit.create(KakaoRetrofitService::class.java)

    fun getKakaoLoginService(): KakaoRetrofitService =
        makeLoginRetrofit().create(KakaoRetrofitService::class.java)

}
