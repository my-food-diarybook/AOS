package com.android.myfooddiarybookaos.model.my

data class Notice(
    private val id: Int,
    private val title: String,
    private val content: String,
    private val available: Boolean,
    private val noticeAt: String // date
)