package com.android.myfooddiarybookaos.data.di

import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class TodayModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTodayRepository(
        todayRepository: TodayRepository
    ) : TodayViewModelInterface
}