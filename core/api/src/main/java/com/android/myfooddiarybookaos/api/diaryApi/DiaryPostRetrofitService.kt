package com.android.myfooddiarybookaos.api.diaryApi

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface DiaryPostRetrofitService {

    @Multipart
    @POST("diary/new")
    fun newDiary(
        @Query("createTime")createTime : String,
        @Part("placeInfo") placeInfo : RequestBody,
        @Part files : List<MultipartBody.Part>
    ): Call<Unit>


    @Multipart
    @POST("diary/{diaryId}/images")
    fun addDiaryImages(
        @Path("diaryId") diaryId : Int,
        @Part file : List<MultipartBody.Part>
    ): Call<Unit>

    @Multipart
    @PATCH("diary/image/{imageId}")
    fun updateDiaryImage(
        @Path("imageId") imageId: Int,
        @Part file : MultipartBody.Part
    ): Call<Unit>
}
