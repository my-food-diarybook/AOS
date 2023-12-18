package com.android.myfooddiarybookaos.api.myApi

import com.android.myfooddiarybookaos.model.my.NoticeData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyRetrofitService {

    @GET("notice/paging")
    suspend fun getPagingNotice(
        @Query("startId") startId: Int,
        @Query("size") size : Int,
        @Query("title") title: String = "공지"
    ): NoticeData
}