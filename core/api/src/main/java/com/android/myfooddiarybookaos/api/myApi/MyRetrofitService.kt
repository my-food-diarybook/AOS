package com.android.myfooddiarybookaos.api.myApi

import retrofit2.http.GET
import retrofit2.http.POST

interface MyRetrofitService {

    @GET("notice/paging")
    fun getPagingNotice(

    )
}