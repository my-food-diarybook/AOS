package com.android.myfooddiarybookaos.detail.viewModel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataDetail.DetailRepository
import com.android.myfooddiarybookaos.data.dataMap.repository.MapSearchRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.map.MyLocation
import com.android.myfooddiarybookaos.model.map.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    private val mapSearchRepository: MapSearchRepository
) : ViewModel() {

    private val _appState = MutableLiveData<ApplicationState>()
    private val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    private val diaryState: LiveData<DiaryState> get() = _diaryState

    private val _diaryDetail = MutableLiveData<DiaryDetail>()
    val diaryDetail: LiveData<DiaryDetail> get() = _diaryDetail

    private val _searchResult = MutableLiveData<List<Place>>()
    val searchResult: LiveData<List<Place>> get() = _searchResult

    private val _currentLocationResult = MutableLiveData<List<Place>>()
    val currentLocationResult: LiveData<List<Place>> get() = _currentLocationResult

    private val _myLocation = MutableLiveData<MyLocation>()
    private val myLocation: LiveData<MyLocation> get() = _myLocation

    fun initAppState(state1: ApplicationState, state2: DiaryState) {
        _appState.value = state1
        _diaryState.value = state2
    }

    fun setDiaryDetail(
        initData: (DiaryDetail?) -> Unit
    ) {
        diaryState.value?.currentDiaryDetail?.let {
            if (it.value != -1) {
                detailRepository.getDetailDiary(
                    it.value,
                    isUpdate = { detail ->
                        _diaryDetail.value = detail
                        initData(detail)
                    }
                )
            }
        }
    }

    fun getSearchResult(
        data: String,
    ) {
        mapSearchRepository.getSearchKeyword(
            data,
            myLocation.value,
            result = {
                _searchResult.value = it?.documents
            }
        )
    }

    fun setMyLocation(
        activity: Activity
    ) {
        mapSearchRepository.initLocation(
            activity,
            setLocation = {
                _myLocation.value = it
                getCurrentLocationData()
            }
        )
    }

    private fun getCurrentLocationData() {
        mapSearchRepository.getCurrentLocationData(
            myLocation.value,
            result = {
                _currentLocationResult.value = it?.documents
            }
        )
    }


    fun goBack() {
        appState.value?.navController?.popBackStack()
        diaryState.value?.resetDiaryDetail()
    }
}