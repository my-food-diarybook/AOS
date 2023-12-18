package com.android.myfooddiarybookaos.data.dataMy.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.myfooddiarybookaos.model.my.Notice

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Notice>)

    @Query("SELECT * FROM myNotices")
    fun getItemPager() : PagingSource<Int, Notice>

    @Query("DELETE FROM myNotices")
    fun clearAll()
}