package com.android.myfooddiarybookaos.api.myApi

import retrofit2.http.GET
import retrofit2.http.Query

interface MyRetrofitService {

    @GET("notice/more")
    suspend fun getPagingNotice(
        @Query("startId") startId: Int,
        @Query("size") size : Int,
    ): NoticeData
}