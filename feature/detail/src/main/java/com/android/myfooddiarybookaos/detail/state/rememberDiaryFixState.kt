package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.*
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.Tag

@Composable
fun rememberDiaryFixState(
    tags: MutableList<Tag> =  mutableListOf() ,
    memo: MutableState<String> =  mutableStateOf("") ,
    diaryTimeData: MutableState<String> = mutableStateOf("ETC"),
    place: MutableState<String?> = mutableStateOf(null),
    longitude: MutableState<Double?> = mutableStateOf(null),
    latitude: MutableState<Double?> = mutableStateOf(null)
) = remember(tags, memo, place, longitude, latitude) {
    DetailFixState(tags, memo, diaryTimeData, place, longitude, latitude)
}