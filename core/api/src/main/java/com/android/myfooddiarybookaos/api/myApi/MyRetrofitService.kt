package com.android.myfooddiarybookaos.api.myApi

import com.android.myfooddiarybookaos.model.statistics.StatisticsList
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyRetrofitService {

    @GET("notice/more")
    suspend fun getPagingNotice(
        @Query("startId") startId: Int,
        @Query("size") size : Int,
    ): NoticeData

    @GET("user/statistics")
    suspend fun getUserStatistics(): StatisticsList

    @POST("user/logout")
    suspend fun logoutUser()

}