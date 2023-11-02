package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.diary.PlaceInfo
import com.android.myfooddiarybookaos.model.login.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface DiaryRetrofitService {

    @Multipart
    @POST("diary/new")
    fun newDiary(
        @Query("createTime")createTime : String,
        @Part placeInfo : PlaceInfo,
        @Part files : List<MultipartBody.Part>
    ): Call<String>

}
