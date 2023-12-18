package com.android.myfooddiarybookaos.api.myApi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myNotices")
data class NoticeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val content: String,
    val available: Boolean,
    val noticeAt: String // date
)