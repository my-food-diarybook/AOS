package com.android.myfooddiarybookaos.detail.viewModel

import android.content.Context
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    private val mapSearchRepository: MapSearchRepository,
    private val imageRepository: ImageRepository // for fix image
) : ViewModel() {

    private val _appState = MutableLiveData<ApplicationState>()
    val appState: LiveData<ApplicationState> get() = _appState

    private val _diaryState = MutableLiveData<DiaryState>()
    val diaryState: LiveData<DiaryState> get() = _diaryState

    private val _diaryDetail = MutableLiveData<DiaryDetail>()
    val diaryDetail: LiveData<DiaryDetail> get() = _diaryDetail

    private val _searchResult = MutableLiveData<List<Place>>()
    val searchResult: LiveData<List<Place>> get() = _searchResult

    // 현재 검색 로케이션
    private val _currentLocationResult = MutableLiveData<List<Place>>()
    val currentLocationResult: LiveData<List<Place>> get() = _currentLocationResult

    // 내 위치 정보
    private val _myLocation = MutableLiveData<MyLocation>()
    private val myLocation: LiveData<MyLocation> get() = _myLocation

    // 현재 폴더
    private val _currentFolder = mutableStateOf<Pair<String, String?>>("최근사진" to null)
    val currentFolder : State<Pair<String, String?>> = _currentFolder

    // 선택 이미지 리스트
    private val _selectedImages = mutableStateListOf<GalleryImage>()
    val selectedImages: SnapshotStateList<GalleryImage> = _selectedImages

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
    ) = mapSearchRepository.checkAndRequestPermissions(context,launcher, result = {permissionResult(it)})

    fun setFixResult(detailFixState: DetailFixState){
        diaryState.value?.currentDiaryDetail?.value?.let {diaryId ->
            detailRepository.fixDetailDiary(
                diaryId,
                detailFixState.diaryToFix(),
                state = { change ->
                    if (change){
                        diaryDetail.value?.let{ diaryDetail->
                            _diaryDetail.value = detailFixState.submitResult(diaryDetail)
                        }
                    }
                }
            )
        }
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