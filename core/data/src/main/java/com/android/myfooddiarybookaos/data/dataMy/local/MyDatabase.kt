package com.android.myfooddiarybookaos.data.dataMy.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.myfooddiarybookaos.api.remoteKey.MyRemoteKeysEntity
import com.android.myfooddiarybookaos.api.myApi.NoticeEntity
import com.dnd_9th_3_android.gooding.data.di.remoteKey.MyRemoteKeysDao

@Database(entities = [NoticeEntity::class, MyRemoteKeysEntity::class]
    , version = 2)
abstract class MyDatabase: RoomDatabase() {

    abstract fun getNoticeDao(): MyDao

    abstract fun remoteKeyDao() : MyRemoteKeysDao

}