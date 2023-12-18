package com.android.myfooddiarybookaos.data.dataMy.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.myApi.NoticeEntity
import com.android.myfooddiarybookaos.data.dataMy.local.MyDatabase
import com.android.myfooddiarybookaos.data.dataMy.remote.MyRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoticeRepository @Inject constructor(
    private val db: MyDatabase,
    private val networkManager: NetworkManager
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getNoticePager(): Flow<PagingData<NoticeEntity>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                db.getNoticeDao().getItemPager()
            },
            remoteMediator = MyRemoteMediator(db,networkManager)
        ).flow
    }
}