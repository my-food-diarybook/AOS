package com.android.myfooddiarybookaos.detail.galleryUi.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.detail.galleryUi.component.GalleryItemContent
import com.android.myfooddiarybookaos.detail.galleryUi.component.GalleryTopBar
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.detail.viewModel.GalleryViewModel
import com.android.myfooddiarybookaos.model.image.GalleryImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun GalleryScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    isMultiSelectView: Boolean,
    prevCount: Int,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val toastPreventState = remember {
        mutableStateOf(false)
    }

    val pagingItems = viewModel.customGalleryPhotoList.collectAsLazyPagingItems()
    LaunchedEffect(viewModel.currentFolder.value) {
        viewModel.getGalleryPagingImages()
    }
    LaunchedEffect(Unit) {
        viewModel.getFolder()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            GalleryTopBar(
                isMultiSelectView = isMultiSelectView,
                prevCount = prevCount,
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
                    if (pagingItems.itemCount > 0) {

                        diaryState.multiPartList = viewModel
                            .getMultiPartFromUri(diaryState.addScreenState.value == AddScreenState.FIX_IMAGE_IN_DETAIL)
                        diaryState.isSelectedGallery.value = true
                        appState.navController.popBackStack()
                    }
                }
            )

            if (pagingItems.itemCount == 0) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "이미지가 존재하지 않습니다.",
                        fontSize = 19.scaledSp(),
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
                ) {
                    items(pagingItems.itemCount) { index ->
                        pagingItems[index]?.let { galleryImage ->
                            GalleryItemContent(
                                galleryImage = galleryImage,
                                selectedImages = viewModel.selectedImages,
                                isMultiSelectView = isMultiSelectView,
                                setSelectImage = { image ->// 이미지 셋
                                    if (viewModel.selectedImages.size + prevCount < 5) {
                                        viewModel.addSelectedImage(image)
                                    } else {
                                        toastPreventState.value = true
                                    }
                                },
                                removeImage = { id ->// 이미지 삭제
                                    viewModel.removeSelectedImage(id)
                                }
                            )
                        }
                    }
                }
            }
        }

        if (toastPreventState.value) {
            Surface(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.toast_back),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Text(
                        text = "사진은 최대5개까지 등록할 수 있어요.",
                        fontWeight = FontWeight.W400,
                        fontFamily = robotoRegular,
                        fontSize = 15.scaledSp(),
                        lineHeight = 20.scaledSp(),
                        letterSpacing = (-0.4).sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 14.dp,
                                end = 7.dp,
                                top = 14.dp,
                                bottom = 12.dp
                            )
                    )
                    rememberCoroutineScope().launch {
                        delay(1000)
                        toastPreventState.value = false
                    }
                }
            }
        }
    }
}