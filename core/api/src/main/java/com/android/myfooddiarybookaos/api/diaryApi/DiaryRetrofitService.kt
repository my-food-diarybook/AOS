package com.android.myfooddiarybookaos.api.diaryApi

import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface DiaryRetrofitService {

    @POST("diary/new")
    fun newDiary(
    ): Call<Unit>

}
