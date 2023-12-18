package com.dnd_9th_3_android.gooding.data.di.remoteKey

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.myfooddiarybookaos.api.remoteKey.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemote(list : List<RemoteKeysEntity>)

    @Query("SELECT * FROM remoteKey WHERE id = :id")
    fun getRemoteKeys(id : Int) : RemoteKeysEntity

    @Query("DELETE FROM remoteKey")
    fun clearAll()
}