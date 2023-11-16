package com.android.myfooddiarybookaos.timeline.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataTimeLine.TimeLineRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    private val timeLineRepository: TimeLineRepository
): ViewModel() {

    private val _appState = MutableLiveData<ApplicationState>()
    private val appState: LiveData<ApplicationState> get() = _appState

    private val _timeLine = MutableLiveData<List<TimeLine>>()
    val timeLine: LiveData<List<TimeLine>> get() = _timeLine

    fun setTimeLineData(
        date: String
    ) {
        timeLineRepository.getTimeLineData(
            date = date,
            isUpdate = {
                it?.let { _timeLine.value = it }
            }
        )
    }



}