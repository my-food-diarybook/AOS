package com.android.myfooddiarybookaos.api.remoteKey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_remoteKey")
data class MyRemoteKeysEntity(
    @PrimaryKey
    val id : Int,
    val prevKey : Int?,
    val nextKey : Int?
)