package com.android.myfooddiarybookaos.home.viewModel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataHome.repository.HomePostRepository
import com.android.myfooddiarybookaos.data.dataHome.repository.HomeRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homePostRepository: HomePostRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _appState = MutableLiveData<ApplicationState>()
    private val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    val diaryState: LiveData<DiaryState> get() = _diaryState

    private val _homeDiaryList = MutableLiveData<List<Diary>>()
    val homeDiaryList: LiveData<List<Diary>> get() = _homeDiaryList

    private val _homeDayInDiary = MutableLiveData<DiaryHomeDay>()
    val homeDayInDiary: LiveData<DiaryHomeDay> get() = _homeDayInDiary

    fun initState(
        state1: ApplicationState,
        state2: DiaryState,
    ) {
        _appState.value = state1
        _diaryState.value = state2
    }

    fun getDiaryList(
        yearMonth: String
    ) {
        homeRepository.getCurrentHomeDiary(
            yearMonth,
            dataState = {
                _homeDiaryList.value = it
            }
        )
    }

    fun getHomeDayInDiary(
        date: String
    ) {
        homeRepository.getCurrentHomeDay(
            date,
            dataState = {
                _homeDayInDiary.value = it
            }
        )
    }

    fun makeNewDiary(
        createTime: String,
        files: List<MultipartBody.Part>,
        diaryState: (Boolean) -> Unit
    ) {
        homePostRepository.postNewDiary(
            createTime,
            null, null, null,
            files,
            isSuccess = {
                diaryState(it)
            }
        )
    }

    fun addDiaryImage(
        diaryId: Int,
        file: List<MultipartBody.Part>,
        addState: (Boolean) -> Unit
    ) {
        homePostRepository.postDiaryImage(
            diaryId, file,
            isSuccess = { result ->
                addState(result)
            }
        )
    }

    fun getMultiPartFromBitmap(
        cameraBitmap: Bitmap
    ): List<MultipartBody.Part> {
        return homePostRepository.makePartListFromBitmap(cameraBitmap)
    }

    fun getMultiPartFromUri(
        uriList: List<Uri>
    ): List<MultipartBody.Part> {
        return homePostRepository.makePartListFromUri(uriList)
    }

    fun getCurrentDiary(
        yearMonth: String?,
        day: String
    ): Diary? {
        val currentDay = if (day.length == 1) "0$day" else day
        val timeData = "$yearMonth-$currentDay"
        return homeDiaryList.value?.find {
            it.time == timeData
        }
    }

    fun goHomeDayView() {
        appState.value?.navController?.navigate(ScreenRoot.HOME_DAY)
    }

    fun getHomeDaySize(): Int = homeDiaryList.value?.size ?: 0

}