package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.*
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.Tag

@Composable
fun rememberDiaryFixState(
    diaryDetail: DiaryDetail?,
    tags: MutableList<Tag> = if (diaryDetail?.tags == null) mutableListOf() else diaryDetail.tags.toMutableStateList(),
    memo: MutableState<String> = if (diaryDetail?.memo == null) mutableStateOf("") else mutableStateOf(diaryDetail.memo),
    place: MutableState<String?> = mutableStateOf(diaryDetail?.place.orEmpty()),
    longitude: MutableState<Double?> = mutableStateOf(diaryDetail?.longitude),
    latitude: MutableState<Double?> = mutableStateOf(diaryDetail?.latitude)
) = remember(tags, memo, place, longitude, latitude) {

    fun setMemo(
        memoFlow: String
    ) {
        memo.value = memoFlow
    }
    fun checkMemo(): Boolean = memo.value.isBlank()

    fun addTag(newTag: Tag) {
        tags.add(newTag)
    }
    fun checkTag(): Boolean = tags.isEmpty()

    fun addPlace(
        newPlace: String,
        newLongitude: Double,
        newLatitude: Double
    ){
        place.value = newPlace
        longitude.value = newLongitude
        latitude.value = newLatitude
    }
    fun checkLocation(): Boolean = place.value.isNullOrBlank()

}