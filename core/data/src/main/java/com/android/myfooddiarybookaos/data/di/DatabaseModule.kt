package com.android.myfooddiarybookaos.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
        )
            .addMigrations(
                migrations = arrayOf(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE remoteKey RENAME to my_remoteKey")
                    }

                })
            )
            .build()

}
