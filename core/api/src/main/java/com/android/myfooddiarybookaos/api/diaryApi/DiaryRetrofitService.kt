package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.YearMonth

interface DiaryRetrofitService {

    @GET("diary/home")
    fun getHomeDiary(
        @Query("yearMonth") yearMonth: String
    ) : Call<List<Diary>>

    @GET("diary/home-day")
    fun getHomeDay(
        @Query("date") date: String
    ): Call<DiaryHomeDay>

    @GET("diary/{diaryId}")
    fun getDiaryDetail(
        @Path("diaryId") diaryId : Int,
    ): Call<DiaryDetail>
}