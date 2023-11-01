package com.android.myfooddiarybookaos.data.dataHome.repository

import com.android.myfooddiarybookaos.api.NetworkManager

class HomeRepository(
    private val networkManager: NetworkManager
) {
    private val manager = networkManager.getDiaryApiService()
}