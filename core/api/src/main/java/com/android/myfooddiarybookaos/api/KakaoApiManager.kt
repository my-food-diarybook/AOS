package com.android.myfooddiarybookaos.api

import com.android.myfooddiarybookaos.api.kakao.KakaoRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class KakaoApiManager() {
    companion object{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val loginRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(KAKAO_LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getKakaoMapService(): KakaoRetrofitService =
        retrofit.create(KakaoRetrofitService::class.java)

    fun getKakaoLoginService(): KakaoRetrofitService =
        loginRetrofit.create(KakaoRetrofitService::class.java)

}