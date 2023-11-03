package com.android.myfooddiarybookaos.data.di

import android.content.Context
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.data.dataHome.repository.HomeRepository
import com.android.myfooddiarybookaos.data.dataLogin.repository.LoginRepository
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import dagger.Binds
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
    fun provideHomeRepository(
        networkManager: NetworkManager,
        @ApplicationContext context: Context
    ) = HomeRepository(networkManager,context)

}