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
        @Query("x") x: String?,
        @Query("y") y: String?,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 15,
    ): Call<ResultSearchKeyword>

    @GET("v2/local/search/category.json")
    fun getCurrentLocationKeyword(
        @Header("Authorization") key: String = KAKAO_API_KEY ,
        @Query("category_group_code") category_group_code: String = "FD6",
        @Query("x") x: String?,
        @Query("y") y: String?,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): Call<ResultSearchKeyword>
}