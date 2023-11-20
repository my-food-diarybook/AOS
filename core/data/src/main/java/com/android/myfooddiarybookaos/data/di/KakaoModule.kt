package com.android.myfooddiarybookaos.data.di

import android.content.Context
import com.android.myfooddiarybookaos.api.KakaoApiManager
import com.android.myfooddiarybookaos.api.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object KakaoModule {

    @Singleton
    @Provides
    fun provideKakaoManager() = KakaoApiManager()
}