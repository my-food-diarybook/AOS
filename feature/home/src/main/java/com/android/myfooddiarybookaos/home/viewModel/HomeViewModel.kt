package com.android.myfooddiarybookaos.home.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.myfooddiarybookaos.data.dataHome.repository.HomePostRepository
import com.android.myfooddiarybookaos.data.dataHome.repository.HomeRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.diary.Diary
import com.android.myfooddiarybookaos.model.home.DiaryHomeDay
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homePostRepository: HomePostRepository,
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _appState = MutableLiveData<ApplicationState>()
    private val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    val diaryState: LiveData<DiaryState> get() = _diaryState


    private val _homeDiaryList = MutableStateFlow<List<Diary>>(emptyList())
    val homeDiaryList: StateFlow<List<Diary>> = _homeDiaryList.asStateFlow()

    private val _homeDayInDiary = MutableStateFlow<DiaryHomeDay>(DiaryHomeDay())
    val homeDayInDiary: StateFlow<DiaryHomeDay> = _homeDayInDiary.asStateFlow()


    fun initState(
        state1: ApplicationState,
        state2: DiaryState,
    ) {
        _appState.value = state1
        _diaryState.value = state2
    }


    fun setDiaryList(
        yearMonth: String
    ) = viewModelScope.launch {
        homeRepository.getCurrentHomeDiary(yearMonth)
            .collectLatest {
                _homeDiaryList.value = it
            }
    }

    fun getHomeDayInDiary(
        date: String
    ) = viewModelScope.launch {
        homeRepository.getCurrentHomeDay(date)
            .collectLatest {
                _homeDayInDiary.value = it
            }
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

    // 비트맵 -> 멀티파트
    fun getMultiPartFromBitmap(
        cameraBitmap: Bitmap
    ): List<MultipartBody.Part> = homePostRepository.makePartListFromBitmap(cameraBitmap)

//    fun getMultiPartFromUri(
//        uriList: List<Uri>
//    ): List<MultipartBody.Part> {
//        return homePostRepository.makePartListFromUri(uriList)
//    }

    fun getCurrentDiary(
        yearMonth: String?,
        day: String
    ): Diary? {
        val currentDay = if (day.length == 1) "0$day" else day
        val timeData = "$yearMonth-$currentDay"
        return homeDiaryList.value.find { it.time == timeData }
    }

    fun goHomeDayView() {
        appState.value?.navController?.navigate(ScreenRoot.HOME_DAY)
    }

    fun getPrevHomeDay() = homeDayInDiary.value.beforeDay ?: ""
    fun getNextHomeDay() = homeDayInDiary.value.afterday ?: ""
    fun getHomeDays() = homeDayInDiary.value.homeDayList

    fun goDetailView(diaryId: Int) {
        diaryState.value?.setDiaryDetail(diaryId)
        appState.value?.navController?.navigate(ScreenRoot.DETAIL_DIARY)
    }

    fun getHomeDaySize(): Int = homeDiaryList.value.size

}