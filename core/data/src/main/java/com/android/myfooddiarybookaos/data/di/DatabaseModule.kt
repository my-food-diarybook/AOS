package com.android.myfooddiarybookaos.data.di

import android.content.Context
import androidx.room.Room
import com.android.myfooddiarybookaos.data.dataMy.local.MyDao
import com.android.myfooddiarybookaos.data.dataMy.local.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesMyDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "myDatabase"
        ).build()

}