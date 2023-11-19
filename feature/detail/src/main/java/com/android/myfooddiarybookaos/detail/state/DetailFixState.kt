package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    fun initData(
        diaryDetail: DiaryDetail
    ){
        tags = diaryDetail.tags.toMutableList()
        memo.value = diaryDetail.memo
        diaryTimeData.value = diaryDetail.diaryTime
        place.value = diaryDetail.place
        longitude.value = diaryDetail.longitude
        latitude.value = diaryDetail.latitude
    }

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