package com.android.myfooddiarybookaos.data.dataHome.repository

import android.content.Context
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository(
    private val networkManager: NetworkManager,
    @ApplicationContext private val context: Context
) {
    private val manager = networkManager.getDiaryAppApiService()

    suspend fun getCurrentHomeDiary(yearMonth: String): Flow<List<Diary>> = flow {
        emit(manager.getHomeDiary(yearMonth))
    }

    suspend fun getCurrentHomeDay(date: String): Flow<DiaryHomeDay> = flow {
        emit(manager.getHomeDay(date))
    }
}
