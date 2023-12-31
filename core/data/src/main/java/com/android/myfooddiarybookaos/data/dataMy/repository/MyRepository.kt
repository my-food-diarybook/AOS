package com.android.myfooddiarybookaos.data.dataMy.repository

import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.statistics.StatisticsList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyRepository @Inject constructor(
    networkManager: NetworkManager
) {
    private val manager = networkManager.getMyApiService()

    suspend fun getUserStatistics() : Flow<StatisticsList> = flow {
        try {
            emit(manager.getUserStatistics())
        } catch (_: Exception){ }
    }

}