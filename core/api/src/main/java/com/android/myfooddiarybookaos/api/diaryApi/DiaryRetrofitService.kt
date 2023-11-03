package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.diary.PlaceInfo
import com.android.myfooddiarybookaos.model.diary.PlaceInfoBody
import com.android.myfooddiarybookaos.model.login.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface DiaryRetrofitService {

    @Multipart
    @POST("diary/new")
    fun newDiary(
        @Query("createTime")createTime : String,
        @Part("placeInfo") placeInfo : RequestBody,
        @Part files : List<MultipartBody.Part>
    ): Call<Unit>

}
