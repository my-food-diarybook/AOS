package com.android.myfooddiarybookaos.data.di

import android.content.Context
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkManager(
        @ApplicationContext context : Context
    ) = NetworkManager(context)

}