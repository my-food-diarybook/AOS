package com.android.myfooddiarybookaos.detail.state

import androidx.compose.runtime.MutableState
import com.android.myfooddiarybookaos.model.detail.Tag

class DetailFixState(
    private val tags: MutableList<Tag>,
    private val memo: MutableState<String>,
    private val timeData: MutableState<String>,
    private val place: MutableState<String?>,
    private val longitude: MutableState<Double?>,
    private val latitude: MutableState<Double?>
) {
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
}