package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.Tag

class DetailFixState(
    var tags: MutableList<Tag>,
    var memo: MutableState<String>,
    var diaryTimeData: MutableState<String>,
    var place: MutableState<String?>,
    var longitude: MutableState<Double?>,
    var latitude: MutableState<Double?>
) {
    fun initMemo(
        diaryDetail: DiaryDetail?
    ){
        tags = if (diaryDetail?.tags == null) mutableListOf() else diaryDetail.tags.toMutableStateList()
        memo = if (diaryDetail?.memo == null) mutableStateOf("") else mutableStateOf(diaryDetail.memo)
        diaryTimeData = if (diaryDetail?.diaryTime == null) mutableStateOf("ETC") else mutableStateOf(diaryDetail.diaryTime)
        place = mutableStateOf(diaryDetail?.place.orEmpty())
        longitude = mutableStateOf(diaryDetail?.longitude)
        latitude = mutableStateOf(diaryDetail?.latitude)
    }

    fun setMemo(
        memoFlow: String
    ) {
        memo.value = memoFlow
    }

    fun checkMemo(): Boolean = memo.value.isBlank()

    fun addTag(newTag: String) {
        tags.add(Tag((tags.lastOrNull()?.id ?: 0) + 1, newTag))
    }
    fun removeTag(tag: Tag){
        tags.remove(tag)
    }

    fun checkTag(): Boolean = tags.isEmpty()

    fun addPlace(
        newPlace: String,
        newLongitude: Double,
        newLatitude: Double
    ) {
        place.value = newPlace
        longitude.value = newLongitude
        latitude.value = newLatitude
    }

    fun checkLocation(): Boolean = place.value.isNullOrBlank()

    fun setTimeData(new: MutableState<String> ){
        diaryTimeData.value =  new.value
    }
}