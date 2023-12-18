package com.android.myfooddiarybookaos.api.myApi

import androidx.room.Entity

@Entity(tableName = "mainFeeds")
data class NoticeEntity(
    val id: Int,
    val title: String,
    val content: String,
    val available: Boolean,
    val noticeAt: String // date
)