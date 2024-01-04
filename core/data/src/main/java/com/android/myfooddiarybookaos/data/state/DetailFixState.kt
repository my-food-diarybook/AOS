package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.detail.FixDiary
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

    fun checkChangeData(
        diaryDetail: DiaryDetail
    ): Boolean{
        return tags.joinToString { "," } != diaryDetail.tags.joinToString { "," } ||
                memo.value != diaryDetail.memo ||
                    diaryTimeData.value != diaryDetail.diaryTime ||
                        place.value != diaryDetail.place
    }

    fun setMemo(
        memoFlow: String
    ) {
        memo.value = memoFlow
    }

    fun checkMemo(): Boolean = memo.value.isBlank()

    fun addTag(newTag: String) {
        tags.add(Tag(null, newTag))
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

    fun diaryToFix(): FixDiary{
        return FixDiary(
            memo = memo.value,
            diaryTime = diaryTimeData.value,
            place = place.value,
            longitude = longitude.value,
            latitude = longitude.value,
            tags = tags,
        )
    }

    fun submitResult(diaryDetail: DiaryDetail): DiaryDetail {
        return DiaryDetail(
            memo = memo.value,
            diaryTime = diaryTimeData.value,
            tags = tags,
            place = place.value,
            latitude = longitude.value,
            longitude = longitude.value,
            date = diaryDetail.date,
            images = diaryDetail.images
        )
    }

}