package com.android.myfooddiarybookaos.data.dataTimeLine.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.myfooddiarybookaos.api.diaryApi.TimeLineRetrofitService
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary

class TimeLineDiaryPagingSource(
    private val date: String,
    private val manager: TimeLineRetrofitService,
    private val diarySize: Int
): PagingSource<Int,TimeLineDiary>() {
    override fun getRefreshKey(state: PagingState<Int, TimeLineDiary>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TimeLineDiary> {
        return try {
            val next = params.key ?: 0
            val response = manager.getTimeLineFlicking(
                date = date,
                offset = next
            )
//            if (response.isEmpty()) throw Exception()

            LoadResult.Page(
                data = response,
                prevKey = if (next == 0 ) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}