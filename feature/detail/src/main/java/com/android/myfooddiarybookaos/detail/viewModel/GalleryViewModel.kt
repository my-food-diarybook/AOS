package com.android.myfooddiarybookaos.detail.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.android.myfooddiarybookaos.data.dataGallery.domain.ImageRepository
import com.android.myfooddiarybookaos.model.image.GalleryImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    // 이미지 리스트
    private val _customGalleryPhotoList =
        MutableStateFlow<PagingData<GalleryImage>>(PagingData.empty())

    val customGalleryPhotoList : StateFlow<PagingData<GalleryImage>> =
        _customGalleryPhotoList.asStateFlow()

    // 현재 폴더
    private val _currentFolder = mutableStateOf<Pair<String, String?>>("최근사진" to null)
    val currentFolder : State<Pair<String, String?>> = _currentFolder

    // 선택 이미지 리스트
    private val _selectedImages = mutableStateListOf<GalleryImage>()
    val selectedImages: SnapshotStateList<GalleryImage> = _selectedImages


}