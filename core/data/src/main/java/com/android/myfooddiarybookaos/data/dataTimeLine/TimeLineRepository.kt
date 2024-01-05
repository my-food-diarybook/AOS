package com.android.myfooddiarybookaos.data.dataTimeLine

import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TimeLineRepository @Inject constructor(
    private val networkManager: NetworkManager
) {
    private val manager = networkManager.getTimeLineApiService()

    suspend fun getTimeLineData(date: String): Flow<List<TimeLine>>  = flow {
        try{
            emit(manager.getTimeLineShow(date))
        } catch (_:Exception){
            emit(emptyList())
        }
    }

    fun getTimeLineMoreData(
        date: String,
        offset: Int,
        isUpdate: (List<TimeLineDiary>?) -> Unit
    ) {
        manager.getTimeLineFlicking(date, offset)
//            .enqueue(object : Callback<List<TimeLineDiary>> {
//                override fun onResponse(
//                    call: Call<List<TimeLineDiary>>,
//                    response: Response<List<TimeLineDiary>>
//                ) {
//                    isUpdate(response.body())
//                }
//
//                override fun onFailure(call: Call<List<TimeLineDiary>>, t: Throwable) {
//                    isUpdate(null)
//                }
//
//            })
    }
}