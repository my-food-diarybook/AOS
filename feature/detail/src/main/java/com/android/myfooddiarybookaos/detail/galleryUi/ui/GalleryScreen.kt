package com.android.myfooddiarybookaos.detail.galleryUi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.detail.galleryUi.component.GalleryTopBar
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.model.image.GalleryImage

@Composable
fun GalleryScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        GalleryTopBar(
            selectedImages = selectedImages,
            currentDirectory = ,
            directories = ,
            setCurrentDirectory = ,
            backStage = { /*TODO*/ }) {

        }
    }
}