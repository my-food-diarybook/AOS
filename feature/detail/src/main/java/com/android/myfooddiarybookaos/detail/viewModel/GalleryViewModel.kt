package com.android.myfooddiarybookaos.detail.viewModel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myfooddiarybookaos.data.dataGallery.domain.ImageRepository
import com.android.myfooddiarybookaos.data.dataGallery.remote.GalleryPagingSource
import com.android.myfooddiarybookaos.data.dataGallery.remote.GalleryPagingSource.Companion.PAGING_SIZE
import com.android.myfooddiarybookaos.data.dataHome.repository.HomePostRepository
import com.android.myfooddiarybookaos.model.image.GalleryImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val homePostRepository: HomePostRepository,
    private val imageRepository: ImageRepository
): ViewModel() {
    // 이미지 리스트
    private val _customGalleryPhotoList =
        MutableStateFlow<PagingData<GalleryImage>>(PagingData.empty())

    val customGalleryPhotoList : StateFlow<PagingData<GalleryImage>> =
        _customGalleryPhotoList.asStateFlow()

    // 폴더 리스트
    private val _folders = mutableStateListOf<Pair<String,String?>>("최근사진" to null)
    val folders get() = _folders

    // 현재 폴더
    private val _currentFolder = mutableStateOf<Pair<String, String?>>("최근사진" to null)
    val currentFolder : State<Pair<String, String?>> = _currentFolder

    // 선택 이미지 리스트
    private val _selectedImages = mutableStateListOf<GalleryImage>()
    val selectedImages: SnapshotStateList<GalleryImage> = _selectedImages

    // 페이징 처리 함수
    fun getGalleryPagingImages() = viewModelScope.launch {
        _customGalleryPhotoList.value = PagingData.empty()
        Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GalleryPagingSource(
                    imageRepository = imageRepository,
//                    currentLocation =  null
                    // null -> 모든 위치 사진 가져오기,
                    currentLocation = currentFolder.value.second,
                    // currentFolder.value... -> 해당 위치 사진 가져오기
                )
            },
        ).flow.cachedIn(viewModelScope).collectLatest {
            _customGalleryPhotoList.value = it
        }
    }

    // 현재 폴더 위치 변경
    fun setCurrentFolder(location : Pair<String, String?>){
        _currentFolder.value = location
    }

    // 현재 폴더 리스트 저장
    fun getFolder(){
        imageRepository.getFolderList().map {
            _folders.add(it.split("/").last() to it)
        }
    }

    // 새 이미지 추가
    fun addSelectedImage(image :GalleryImage) {
        _selectedImages.add(image)
    }

    fun removeSelectedImage(id : Long){
        val removedImage = _selectedImages.find { it.id == id }
        removedImage?.let {
            _selectedImages.remove(removedImage)
        }
    }

    fun getMultiPartFromUri(isOneImage: Boolean): List<MultipartBody.Part> {
        return homePostRepository.makePartListFromUri(
            selectedImages.map { it.uri },
            isOneImage
        )
    }

}