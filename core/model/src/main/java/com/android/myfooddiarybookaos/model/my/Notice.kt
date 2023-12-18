package com.android.myfooddiarybookaos.model.my

data class Notice(
    val id: Int,
    val title: String,
    val content: String,
    val available: Boolean,
    val noticeAt: String // date
)