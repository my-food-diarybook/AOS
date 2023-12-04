package com.android.myfooddiarybookaos.data.dataDetail

import android.util.Log
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.state.DetailFixState
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.FixDiary
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DetailRepository @Inject constructor(
    private val networkManager: NetworkManager
) {
    private val manager = networkManager.getDiaryAppApiService()
    private val postManager = networkManager.getDiaryMultiPartApiService()

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

    fun fixDetailDiary(
        diaryId: Int,
        fixDiary: FixDiary,
        state : (Boolean) -> Unit
    ){
        manager.setDiaryMemo(diaryId,fixDiary)
            .enqueue(object : Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) state(true)
                    else state(false)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    state(true)
                }

            })
    }

    fun fixDetailDiaryImage(
        imageId: Int,
        file: MultipartBody.Part,
        state: (Boolean) -> Unit
    ){
        postManager.updateDiaryImage(
            imageId,file
        ).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) state(true)
                else state(false)
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                state(false)
            }

        })
    }

    fun addDetailDiaryImages(
        diaryId : Int,
        fileList : List<MultipartBody.Part>,
        isSuccess : (Boolean) -> Unit
    ){
        try{
            postManager.addDiaryImages(
                diaryId,
                fileList
            ).enqueue(object : Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) isSuccess(true)
                    else isSuccess(false)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    isSuccess(false)
                }

            })
        } catch (e: Exception){ isSuccess(false) }
    }

    suspend fun deleteDiary(
        diaryId: Int,
    ){
        manager.deleteDiary(diaryId)
    }
}