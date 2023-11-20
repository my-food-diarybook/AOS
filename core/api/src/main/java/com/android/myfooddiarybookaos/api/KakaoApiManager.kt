package com.android.myfooddiarybookaos.api

import android.content.Context
import com.android.myfooddiarybookaos.api.map.KakaoRetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KakaoApiManager(
    private val context: Context
) {
    companion object{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getKakaoService(): KakaoRetrofitService =
        retrofit.create(KakaoRetrofitService::class.java)
}