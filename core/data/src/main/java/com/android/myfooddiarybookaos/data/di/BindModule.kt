package com.android.myfooddiarybookaos.data.di

import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.data.dataGallery.domain.ImageRepository
import com.android.myfooddiarybookaos.data.dataGallery.repository.ImageRepositoryImpl
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTodayRepository(
        todayRepository: TodayRepository
    ) : TodayViewModelInterface

    @Binds
    @ViewModelScoped
    abstract fun bindImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ) : ImageRepository
}