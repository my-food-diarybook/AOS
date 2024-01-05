package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeLineRetrofitService {

    @GET("timeline/show")
    suspend fun getTimeLineShow(
        @Query("date") date: String
    ): List<TimeLine>

    @GET("timeline/show/more-diary")
    fun getTimeLineFlicking(
        @Query("date") date: String,
        @Query("offset") offset: Int
    ): List<TimeLineDiary>

}