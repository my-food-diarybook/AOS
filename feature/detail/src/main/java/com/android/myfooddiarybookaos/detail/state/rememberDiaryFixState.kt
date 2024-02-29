package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.android.myfooddiarybookaos.data.state.DetailFixState
import com.android.myfooddiarybookaos.model.detail.Tag

@Composable
fun rememberDiaryFixState(
    tags: MutableList<Tag> = mutableListOf(),
    memo: MutableState<String> = mutableStateOf(""),
    diaryTimeData: MutableState<String> = mutableStateOf("ETC"),
    place: MutableState<String?> = mutableStateOf(null),
    longitude: MutableState<Double?> = mutableStateOf(null),
    latitude: MutableState<Double?> = mutableStateOf(null)
) = remember(tags, memo, place, longitude, latitude) {
    DetailFixState(tags, memo, diaryTimeData, place, longitude, latitude)
}
