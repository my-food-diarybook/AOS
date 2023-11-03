package com.android.myfooddiarybookaos.data.dataHome.repository

import android.content.Context
import com.android.myfooddiarybookaos.api.NetworkManager
import dagger.hilt.android.qualifiers.ApplicationContext

class HomeRepository(
    private val networkManager: NetworkManager,
    @ApplicationContext private val context: Context
) {
    private val manager = networkManager.getDiaryAppApiService()
}