package com.android.myfooddiarybookaos.detail.viewModel

import android.content.Context
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.myfooddiarybookaos.data.dataDetail.DetailRepository
import com.android.myfooddiarybookaos.data.dataGallery.domain.ImageRepository
import com.android.myfooddiarybookaos.data.dataMap.repository.MapSearchRepository
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DetailFixState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.detail.DiaryDetail
import com.android.myfooddiarybookaos.model.image.GalleryImage
import com.android.myfooddiarybookaos.model.map.MyLocation
import com.android.myfooddiarybookaos.model.map.Place
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    private val mapSearchRepository: MapSearchRepository,
) : ViewModel() {

    private val _appState = MutableLiveData<ApplicationState>()
    val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    val diaryState: LiveData<DiaryState> get() = _diaryState

    private val _diaryDetail = MutableLiveData<DiaryDetail>()
    val diaryDetail: LiveData<DiaryDetail> get() = _diaryDetail

    private val _searchResult = MutableStateFlow<List<Place>>(emptyList())
    val searchResult: StateFlow<List<Place>> =  _searchResult.asStateFlow()

    // 현재 검색 로케이션
    private val _currentLocationResult = MutableStateFlow<List<Place>>(emptyList())
    val currentLocationResult: StateFlow<List<Place>> = _currentLocationResult.asStateFlow()

    // 내 위치 정보
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
        data: String
    )  = viewModelScope.launch {
        mapSearchRepository.getSearchKeyword(data,myLocation.value)
            .collect {
                _searchResult.value = it
            }
    }

    fun setMyLocation() {
        mapSearchRepository.initLocation(
            setLocation = {
                _myLocation.value = it
                getCurrentLocationData()
            }
        )
    }

    fun requestPermission(
        context: Context,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
        permissionResult: (Boolean) -> Unit
    ) = mapSearchRepository.checkAndRequestPermissions(
        context,
        launcher,
        result = { permissionResult(it) })

    fun setFixResult(
        detailFixState: DetailFixState,
        initCurrentData: () -> Unit
    ) {
        diaryState.value?.currentDiaryDetail?.value?.let { diaryId ->
            detailRepository.fixDetailDiary(
                diaryId,
                detailFixState.diaryToFix(),
                state = { change ->
                    if (change) {
                        diaryDetail.value?.let { diaryDetail ->
                            _diaryDetail.value = detailFixState.submitResult(diaryDetail)
                        }
                        initCurrentData()
                    }
                }
            )
        }
    }

    private fun getCurrentLocationData() = viewModelScope.launch {
        mapSearchRepository.getCurrentLocationData(myLocation.value)
            .collect{
                _currentLocationResult.value = it
            }
    }


    fun addDiaryImages(
        diaryId: Int,
        file: List<MultipartBody.Part>,
        addState: (Boolean) -> Unit
    ) {
        detailRepository.addDetailDiaryImages(
            diaryId, file,
            isSuccess = { result ->
                addState(result)
            }
        )
    }

    fun fixDiaryImage(
        imageId : Int,
        file : MultipartBody.Part,
        addState: (Boolean) -> Unit
    ){
        detailRepository.fixDetailDiaryImage(
            imageId, file,
            state = { result ->
                addState(result)
            }
        )
    }

    fun deleteDiary() = viewModelScope.launch {
        diaryState.value?.currentDiaryDetail?.let {
            detailRepository.deleteDiary(it.value)
            goBack()
        }
    }


    fun goBack() {
        appState.value?.navController?.popBackStack()
        diaryState.value?.resetDiaryDetail()
    }

}