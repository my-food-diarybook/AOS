package com.android.myfooddiarybookaos.api.map

import com.android.myfooddiarybookaos.api.KAKAO_API_KEY
import com.android.myfooddiarybookaos.model.map.ResultSearchKeyword
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoRetrofitService {
    @GET("v2/local/search/keyword.json")
    fun getSearchKeyword(
        @Header("Authorization") key: String = KAKAO_API_KEY ,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<ResultSearchKeyword>
}