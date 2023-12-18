package com.android.myfooddiarybookaos.api.myApi

import retrofit2.http.GET
import retrofit2.http.Query

interface MyRetrofitService {

    @GET("notice/paging")
    suspend fun getPagingNotice(
        @Query("startId") startId: Int,
        @Query("size") size : Int,
        @Query("title") title: String = "공지"
    ): NoticeData
}