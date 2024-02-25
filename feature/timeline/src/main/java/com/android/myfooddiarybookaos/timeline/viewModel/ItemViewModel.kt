package com.android.myfooddiarybookaos.timeline.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.myfooddiarybookaos.data.dataTimeLine.repository.TimeLineRepository
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val timeLineRepository: TimeLineRepository
) : ViewModel() {

    fun setTimeLineData(
        date: String,
        offset: Int,
        diaryList: (List<TimeLineDiary>) -> Unit
    ) = viewModelScope.launch {
        timeLineRepository.getTimeLineMoreData(
            date = date,
            offset = offset
        ).collect {
            diaryList(it)
        }
    }

}
