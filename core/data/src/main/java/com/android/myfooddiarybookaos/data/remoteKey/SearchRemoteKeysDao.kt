package com.android.myfooddiarybookaos.data.remoteKey

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.myfooddiarybookaos.api.remoteKey.MyRemoteKeysEntity


@Dao
interface SearchRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRemote(list : List<SearchRemoteKeysEntity>)

    @Query("SELECT * FROM search_remoteKey WHERE id = :id")
    fun getRemoteKeys(id : Int) : MyRemoteKeysEntity

    @Query("DELETE FROM search_remoteKey")
    fun clearAll()

}