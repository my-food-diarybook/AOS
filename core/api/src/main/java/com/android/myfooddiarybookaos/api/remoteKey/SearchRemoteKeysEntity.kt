package com.android.myfooddiarybookaos.api.remoteKey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_remoteKey")
data class SearchRemoteKeysEntity(
    @PrimaryKey
    val id : Int,
    val prevKey : Int?,
    val nextKey : Int?
)