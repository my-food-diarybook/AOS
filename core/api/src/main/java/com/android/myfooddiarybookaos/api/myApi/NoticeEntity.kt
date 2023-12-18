package com.android.myfooddiarybookaos.api.myApi

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.myfooddiarybookaos.model.my.Notice

@Entity(tableName = "myNotices")
data class NoticeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val content: String,
    val available: Boolean,
    val noticeAt: String // date
){
    fun transNotice(): Notice {
        return Notice(
            id = id,
            title = title,
            content = content,
            available = available,
            noticeAt = noticeAt
        )
    }

}