package com.android.myfooddiarybookaos.data.dataDetail

import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DetailRepository @Inject constructor(
    private val networkManager: NetworkManager
) {
    private val manager = networkManager.getDiaryAppApiService()

    fun getDetailDiary(
        diaryId: Int,
        isUpdate: (DiaryDetail?) -> Unit
    ){
        manager.getDiaryDetail(diaryId)
            .enqueue(object : Callback<DiaryDetail> {
                override fun onResponse(call: Call<DiaryDetail>, response: Response<DiaryDetail>) {
                    isUpdate(response.body())
                }

                override fun onFailure(call: Call<DiaryDetail>, t: Throwable) {
                    isUpdate(null)
                }
            })
    }
}