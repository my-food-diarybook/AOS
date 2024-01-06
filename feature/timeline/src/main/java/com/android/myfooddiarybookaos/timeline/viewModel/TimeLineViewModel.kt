package com.android.myfooddiarybookaos.timeline.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myfooddiarybookaos.data.dataTimeLine.TimeLineRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    private val timeLineRepository: TimeLineRepository
): ViewModel() {

    private val _appState = MutableLiveData<ApplicationState>()
    private val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    private val diaryState: LiveData<DiaryState> get() = _diaryState

    private val _timeLine = MutableStateFlow<PagingData<TimeLine>>(PagingData.empty())
    val timeLine: StateFlow<PagingData<TimeLine>> = _timeLine.asStateFlow()

    fun initState(
        mainAppState: ApplicationState,
        mainDiaryState : DiaryState,
    ){
        _appState.value = mainAppState
        _diaryState.value = mainDiaryState
    }

    fun setTimeLineData(
        date: String
    ) = viewModelScope.launch {
        _timeLine.value = PagingData.empty()
        timeLineRepository.getTimeLineData(date = date)
            .cachedIn(viewModelScope)
            .collectLatest {
                _timeLine.value = it
            }
    }

    fun goDetailView(diaryId: Int) {
        diaryState.value?.setDiaryDetail(diaryId)
        appState.value?.navController?.navigate(ScreenRoot.DETAIL_DIARY)
    }

}