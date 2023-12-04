package com.android.myfooddiarybookaos.data.dataHome.repository

import android.content.Context
import androidx.compose.runtime.State
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(
    private val networkManager: NetworkManager,
    @ApplicationContext private val context: Context
) {
    private val manager = networkManager.getDiaryAppApiService()

    suspend fun getCurrentHomeDiary(yearMonth: String): Flow<List<Diary>> = flow {
        try {
            emit(manager.getHomeDiary(yearMonth))
        } catch (_: Exception) {
        }
    }

    suspend fun getCurrentHomeDay(date: String): Flow<DiaryHomeDay> = flow {
        try {
            emit(manager.getHomeDay(date))
        } catch (_: Exception) {
        }
    }

}