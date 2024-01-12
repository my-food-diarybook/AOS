package com.android.myfooddiarybookaos.timeline.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myfooddiarybookaos.data.dataTimeLine.repository.TimeLineRepository
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
) : ViewModel() {
    private val _timeLineDiary = MutableStateFlow<List<TimeLineDiary>>(emptyList())
    val timeLineDiary: StateFlow<List<TimeLineDiary>> = _timeLineDiary.asStateFlow()

    fun initTimeLiseData(
        dataList : List<TimeLineDiary>
    ){
        _timeLineDiary.value = dataList
    }

    fun setTimeLineData(
        date: String,
        offset: Int
    ) = viewModelScope.launch {
        timeLineRepository.getTimeLineMoreData(
            date = date,
            offset = offset
        ).collectLatest {
            _timeLineDiary.value = it
        }
    }

}