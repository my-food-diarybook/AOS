package com.android.myfooddiarybookaos.timeline.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myfooddiarybookaos.data.dataTimeLine.TimeLineRepository
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val timeLineRepository: TimeLineRepository
): ViewModel() {
    private val _timeLineDiary = MutableStateFlow<PagingData<TimeLineDiary>>(PagingData.empty())
    val timeLineDiary: StateFlow<PagingData<TimeLineDiary>> = _timeLineDiary.asStateFlow()


    fun setTimeLineData(
        date: String,
        diarySize: Int
    ) = viewModelScope.launch {
        timeLineRepository.getTimeLineMoreData(
            date = date,
            diarySize = diarySize
        )
            .cachedIn(viewModelScope)
            .collectLatest {
                _timeLineDiary.value = it
            }
    }

}