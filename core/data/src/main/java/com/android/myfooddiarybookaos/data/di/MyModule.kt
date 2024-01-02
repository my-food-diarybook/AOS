package com.android.myfooddiarybookaos.data.di

import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.dataMy.local.MyDatabase
import com.android.myfooddiarybookaos.data.dataMy.repository.MyRepository
import com.android.myfooddiarybookaos.data.dataMy.repository.NoticeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Singleton
    @Provides
    fun provideMyRepository(
        networkManager: NetworkManager
    ) = MyRepository(networkManager)

}