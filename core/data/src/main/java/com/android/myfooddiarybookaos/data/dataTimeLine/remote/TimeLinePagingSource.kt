package com.android.myfooddiarybookaos.data.dataTimeLine.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.myfooddiarybookaos.api.diaryApi.TimeLineRetrofitService
import com.android.myfooddiarybookaos.model.timeLine.TimeLine

class TimeLinePagingSource(
    date: String,
    private val manager: TimeLineRetrofitService
): PagingSource<Int,TimeLine>() {
    private var currentDate = date
    override fun getRefreshKey(state: PagingState<Int, TimeLine>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TimeLine> {
        return try {
            val next = params.key ?: 0
            val response = manager.getTimeLineShow(
                date = currentDate
            )

            if (response.isEmpty()) throw Exception()
            else {
                val prev = response.last().date.split("-")
                currentDate = "${prev[0]}-${prev[1]}-${(prev[2].toInt()+1)}"
            }

            LoadResult.Page(
                data= response,
                prevKey = if (next == 0) null else next -1,
                nextKey = next + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}