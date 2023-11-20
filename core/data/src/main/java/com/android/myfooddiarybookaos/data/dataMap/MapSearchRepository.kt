package com.android.myfooddiarybookaos.data.dataMap

import com.android.myfooddiarybookaos.api.KakaoApiManager
import com.android.myfooddiarybookaos.model.map.ResultSearchKeyword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapSearchRepository(
    kakaoApiManager: KakaoApiManager
) {
    private val manager = kakaoApiManager.getKakaoService()


    fun getSearchKeyword(
        keyword: String,
        page: Int,
        result: (ResultSearchKeyword?)-> Unit
    ){
        manager.getSearchKeyword(query = keyword,page =  page)
            .enqueue(object : Callback<ResultSearchKeyword>{
                override fun onResponse(
                    call: Call<ResultSearchKeyword>,
                    response: Response<ResultSearchKeyword>
                ) {
                    result(response.body())
                }

                override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                    result(null)
                }

            })
    }
}