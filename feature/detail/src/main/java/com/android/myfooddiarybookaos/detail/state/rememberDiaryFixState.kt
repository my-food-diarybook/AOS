package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.*
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.Tag

@Composable
fun rememberDiaryFixState(
    diaryDetail: DiaryDetail?,
    tags: MutableList<Tag> = if (diaryDetail?.tags == null) mutableListOf() else diaryDetail.tags.toMutableStateList(),
    memo: MutableState<String> = if (diaryDetail?.memo == null) mutableStateOf("") else mutableStateOf(diaryDetail.memo),
    timeData: MutableState<String> = if (diaryDetail?.memo == null) mutableStateOf("ETC") else mutableStateOf(diaryDetail.diaryTime),
    place: MutableState<String?> = mutableStateOf(diaryDetail?.place.orEmpty()),
    longitude: MutableState<Double?> = mutableStateOf(diaryDetail?.longitude),
    latitude: MutableState<Double?> = mutableStateOf(diaryDetail?.latitude)
) = remember(tags, memo, place, longitude, latitude) {
    DetailFixState(tags, memo, timeData, place, longitude, latitude)
}