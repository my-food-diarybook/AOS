package com.android.myfooddiarybookaos.data.di

import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.dataMy.local.MyDatabase
import com.android.myfooddiarybookaos.data.dataMy.repository.NoticeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelScoped::class)
interface MyModule {

    @ViewModelScoped
    @Provides
    fun provideNoticeRepository(
        myDatabase: MyDatabase,
        networkManager: NetworkManager
    ) = NoticeRepository(myDatabase,networkManager)

}