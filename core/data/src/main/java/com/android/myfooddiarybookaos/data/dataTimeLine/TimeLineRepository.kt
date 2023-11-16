package com.android.myfooddiarybookaos.data.dataTimeLine

import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeLineRepository(
    private val networkManager: NetworkManager
) {
    private val manager = networkManager.getTimeLineApiService()

    fun getTimeLineData(
        date: String,
        isUpdate: (List<TimeLine>?) -> Unit
    ) {
        manager.getTimeLineShow(date)
            .enqueue(object : Callback<List<TimeLine>> {
                override fun onResponse(call: Call<List<TimeLine>>, response: Response<List<TimeLine>>) {
                    isUpdate(response.body())
                }

                override fun onFailure(call: Call<List<TimeLine>>, t: Throwable) {
                    isUpdate(null)
                }

            })
    }

    fun getTimeLineMoreData(
        date: String,
        offset: Int,
        isUpdate: (List<TimeLineDiary>?) -> Unit
    ) {
        manager.getTimeLineFlicking(date, offset)
            .enqueue(object : Callback<List<TimeLineDiary>> {
                override fun onResponse(
                    call: Call<List<TimeLineDiary>>,
                    response: Response<List<TimeLineDiary>>
                ) {
                    isUpdate(response.body())
                }

                override fun onFailure(call: Call<List<TimeLineDiary>>, t: Throwable) {
                    isUpdate(null)
                }

            })
    }
}