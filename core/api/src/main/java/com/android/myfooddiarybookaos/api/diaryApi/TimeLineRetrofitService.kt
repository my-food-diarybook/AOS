package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeLineRetrofitService {

    @GET("timeline/show")
    fun getTimeLineShow(
        @Query("date") date: String
    ): Call<List<TimeLine>>

    @GET("timeline/show/more-diary")
    fun getTimeLineFlicking(
        @Query("date") date: String,
        @Query("offset") offset: Int
    ): Call<List<TimeLineDiary>>

}