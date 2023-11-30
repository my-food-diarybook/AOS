package com.android.myfooddiarybookaos.detail.galleryUi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.galleryUi.component.GalleryItemContent
import com.android.myfooddiarybookaos.detail.galleryUi.component.GalleryTopBar
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.detail.viewModel.GalleryViewModel
import com.android.myfooddiarybookaos.model.image.GalleryImage

@Composable
fun GalleryScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    viewModel: GalleryViewModel = hiltViewModel()
) {

    val pagingItems = viewModel.customGalleryPhotoList.collectAsLazyPagingItems()

    LaunchedEffect(viewModel.currentFolder.value){
        viewModel.getGalleryPagingImages()
    }
    LaunchedEffect(Unit) {
        viewModel.getFolder()
    }

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        GalleryTopBar(
            selectedImages = viewModel.selectedImages,
            currentDirectory = viewModel.currentFolder.value,
            directories = viewModel.folders,
            setCurrentDirectory = { folder ->
                viewModel.setCurrentFolder(folder)
            },
            backStage = {
                // back
                appState.navController.popBackStack()
            },
            nextStage = {
                // 저장
                if (pagingItems.itemCount > 0){
                    diaryState.multiPartList = viewModel.getMultiPartFromUri()
                    diaryState.isSelectedGallery.value = true
                    appState.navController.popBackStack()
                }
            }
        )

        if (pagingItems.itemCount == 0){
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(
                    text = "이미지가 존재하지 않습니다.",
                    fontSize = 19.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White),
                columns = GridCells.Fixed(3),
            ){
                items(pagingItems.itemCount){ index ->
                    pagingItems[index]?.let { galleryImage ->
                        GalleryItemContent(
                            galleryImage = galleryImage,
                            selectedImages = viewModel.selectedImages,
                            setSelectImage = { image->// 이미지 셋
                                viewModel.addSelectedImage(image)
                            },
                            removeImage ={ id->// 이미지 삭제
                                viewModel.removeSelectedImage(id)
                            }
                        )
                    }
                }
            }
        }

    }
}