package com.android.myfooddiarybookaos.data.di

import android.content.Context
import com.android.myfooddiarybookaos.api.KakaoApiManager
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.dataCalendar.repository.CustomCalendarRepository
import com.android.myfooddiarybookaos.data.dataDetail.DetailRepository
import com.android.myfooddiarybookaos.data.dataHome.repository.HomePostRepository
import com.android.myfooddiarybookaos.data.dataHome.repository.HomeRepository
import com.android.myfooddiarybookaos.data.dataLogin.repository.LoginRepository
import com.android.myfooddiarybookaos.data.dataMap.repository.MapSearchRepository
import com.android.myfooddiarybookaos.data.dataTimeLine.TimeLineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainModule {

    @ViewModelScoped
    @Provides
    fun provideLoginRepository(
        networkManager: NetworkManager,
        @ApplicationContext context : Context
    ) = LoginRepository(networkManager,context)

    @ViewModelScoped
    @Provides
    fun provideHomePostRepository(
        networkManager: NetworkManager,
        @ApplicationContext context: Context
    ) = HomePostRepository(networkManager,context)

    @ViewModelScoped
    @Provides
    fun provideHomeRepository(
        networkManager: NetworkManager,
        @ApplicationContext context: Context
    ) = HomeRepository(networkManager, context)

    @ViewModelScoped
    @Provides
    fun provideTimeLineRepository(
        networkManager: NetworkManager
    ) = TimeLineRepository(networkManager)


    @ViewModelScoped
    @Provides
    fun provideDetailRepository(
        networkManager: NetworkManager
    ) = DetailRepository(networkManager)

    @Provides
    @ViewModelScoped
    fun bindCustomCalendarRepository()
    = CustomCalendarRepository()

    @Provides
    @ViewModelScoped
    fun bindMapSearchRepository(
        kakaoApiManager: KakaoApiManager,
        @ApplicationContext context: Context
    ) = MapSearchRepository(kakaoApiManager,context)
}