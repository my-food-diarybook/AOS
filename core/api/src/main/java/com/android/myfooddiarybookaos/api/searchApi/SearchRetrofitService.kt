package com.android.myfooddiarybookaos.api.searchApi

import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.model.search.SearchDiary
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRetrofitService {

    @GET("search/condition")
    suspend fun searchCondition(
        @Query("searchCond") searchCond: String
    ): Response<List<SearchCategory>>

    @GET("search")
    suspend fun searchShow(
        @Query("offset") offset: Int
    ): Response<List<SearchCategory>>

    @GET("search/more-diary")
    suspend fun searchMoreDiary(
        @Query("categoryName") categoryName: String,
        @Query("categoryType") categoryType: String,
        @Query("offset") offset: Int
    ): Response<List<SearchDiary>>

}