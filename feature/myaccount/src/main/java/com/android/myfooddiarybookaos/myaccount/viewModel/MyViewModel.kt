package com.android.myfooddiarybookaos.myaccount.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.android.myfooddiarybookaos.api.myApi.NoticeEntity
import com.android.myfooddiarybookaos.data.dataMy.repository.NoticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val noticeRepository: NoticeRepository
): ViewModel(){

    private val _noticeList =
        MutableStateFlow<PagingData<NoticeEntity>>(PagingData.empty())
    var noticeList: Flow<PagingData<NoticeEntity>> =
        _noticeList.asStateFlow()

    init {
        getNotice()
    }

    private fun getNotice() = viewModelScope.launch {
        noticeList = noticeRepository.getNoticePager()
    }
}