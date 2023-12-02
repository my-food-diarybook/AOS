package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.FixDiary
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

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

    @PUT("diary/{diaryId}/memo")
    fun setDiaryMemo(
        @Path("diaryId") diaryId: Int,
        @Body body: FixDiary
    ): Call<Unit>

}