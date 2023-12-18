package com.android.myfooddiarybookaos.api.remoteKey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKey")
data class RemoteKeysEntity(
    @PrimaryKey
    val repoId : Int,
    val prevKey : Int?,
    val nextKey : Int?
)