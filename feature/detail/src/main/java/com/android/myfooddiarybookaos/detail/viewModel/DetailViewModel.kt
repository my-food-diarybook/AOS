package com.android.myfooddiarybookaos.detail.viewModel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataDetail.DetailRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {

    private val _appState = MutableLiveData<ApplicationState>()
    private val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    private val diaryState: LiveData<DiaryState> get() = _diaryState

    private val _diaryDetail = MutableLiveData<DiaryDetail>()
    val diaryDetail: LiveData<DiaryDetail> get() = _diaryDetail

    fun initAppState(state1: ApplicationState, state2: DiaryState) {
        _appState.value = state1
        _diaryState.value = state2
    }

    fun setDiaryDetail(
        setData : (detail: DiaryDetail?) -> Unit
    ) {
        diaryState.value?.currentDiaryDetail?.let {
            if (it.value != -1) {
                detailRepository.getDetailDiary(
                    it.value,
                    isUpdate = { detail ->
                        _diaryDetail.value = detail
                        setData(detail)
                    }
                )
            }
        }
    }

    fun goBack() {
        appState.value?.navController?.popBackStack()
        diaryState.value?.resetDiaryDetail()
    }
}