package com.android.myfooddiarybookaos.data.dataMy.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.android.myfooddiarybookaos.api.remoteKey.MyRemoteKeysEntity
import com.android.myfooddiarybookaos.api.myApi.NoticeEntity
import com.dnd_9th_3_android.gooding.data.di.remoteKey.MyRemoteKeysDao

@Database(
    version = 2,
    entities = [NoticeEntity::class, MyRemoteKeysEntity::class],
    exportSchema = false,
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 2,
//            spec = MyDatabase.MyAutoMigration::class
//        )
//    ]
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getNoticeDao(): MyDao

    abstract fun remoteKeyDao(): MyRemoteKeysDao

//    @RenameColumn(
//        tableName = "myDatabase",
//        fromColumnName = "remoteKeys",
//        toColumnName = "my_remoteKey"
//    )
//    class MyAutoMigration : AutoMigrationSpec

}