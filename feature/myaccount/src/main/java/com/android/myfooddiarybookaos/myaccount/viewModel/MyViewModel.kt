package com.android.myfooddiarybookaos.myaccount.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myfooddiarybookaos.api.myApi.NoticeEntity
import com.android.myfooddiarybookaos.data.dataMy.repository.MyRepository
import com.android.myfooddiarybookaos.data.dataMy.repository.NoticeRepository
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.data.state.LoadState
import com.android.myfooddiarybookaos.model.statistics.StatisticsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val noticeRepository: NoticeRepository,
    private val myRepository: MyRepository
) : ViewModel() {

    private var _state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Init)
    val state: StateFlow<LoadState> = _state.asStateFlow()

    private val _noticeList =
        MutableStateFlow<PagingData<NoticeEntity>>(PagingData.empty())
    val noticeList: Flow<PagingData<NoticeEntity>> = _noticeList.asStateFlow()

    private val _userStatistics =
        MutableStateFlow<StatisticsList>(StatisticsList(listOf(), 0))
    val userStatistics: StateFlow<StatisticsList> = _userStatistics.asStateFlow()

    init {
        getStatistics()
        getNotice()
    }

    fun resetView(){
        getStatistics()
        getNotice()
    }

    private fun getNotice() = viewModelScope.launch {
        noticeRepository.getNoticePager()
            .cachedIn(viewModelScope)
            .collectLatest {
                _noticeList.value = it
            }
    }

    private fun getStatistics() = viewModelScope.launch {
        myRepository.getUserStatistics().collectLatest {
            _userStatistics.value = it
        }
    }

    fun getDiaryCount(categoryName: String): Int {
        return userStatistics.value.diarySubStatisticsList.find {
            it.diaryTime == DiaryTime.getCode(categoryName)
        }?.count ?: 0
    }

    fun userLogout(state: (Boolean) -> Unit) {
        viewModelScope.launch {
            state(myRepository.userLogout())
        }
    }

}
