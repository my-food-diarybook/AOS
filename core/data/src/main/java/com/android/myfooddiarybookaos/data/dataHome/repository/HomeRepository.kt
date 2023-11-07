package com.android.myfooddiarybookaos.data.dataHome.repository

import android.content.Context
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(
    private val networkManager: NetworkManager,
    @ApplicationContext private val context: Context
) {
    private val manager = networkManager.getDiaryAppApiService()

    fun getCurrentHomeDiary(
        yearMonth : String,
        dataState : (List<Diary>?)-> Unit
    ){
        manager.getHomeDiary(yearMonth)
            .enqueue(object : Callback<List<Diary>>{
                override fun onResponse(call: Call<List<Diary>>, response: Response<List<Diary>>) {
                    if (response.isSuccessful){
                        dataState(response.body())
                    }else{
                        dataState(null)
                    }
                }

                override fun onFailure(call: Call<List<Diary>>, t: Throwable) {
                    dataState(null)
                }

            })
    }

    fun getCurrentHomeDay(
        date: String,
        dataState: (DiaryHomeDay?)-> Unit
    ){
        manager.getHomeDay(date)
            .enqueue(object : Callback<DiaryHomeDay>{
                override fun onResponse(
                    call: Call<DiaryHomeDay>,
                    response: Response<DiaryHomeDay>
                ) {
                    dataState(response.body())
                }

                override fun onFailure(call: Call<DiaryHomeDay>, t: Throwable) {
                    dataState(null)
                }

            })
    }

}