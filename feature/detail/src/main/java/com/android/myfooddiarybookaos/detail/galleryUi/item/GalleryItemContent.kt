package com.android.myfooddiarybookaos.detail.galleryUi.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.android.myfooddiarybookaos.model.image.GalleryImage
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun GalleryItemContent(
    galleryImage: GalleryImage,
    selectedImages: List<GalleryImage>,
    setSelectImage: (GalleryImage) -> Unit,
    removeImage: (Long) -> Unit
) {
    val isSelected = selectedImages.find { it.id == galleryImage.id } != null
    val selectedModifier =
        if (isSelected) Modifier
            .aspectRatio(1f)
            .animateContentSize()
            .border(4.dp, colorResource(id = R.color.main_color))
            .clickable {
                removeImage(galleryImage.id)
            }
        else Modifier
            .aspectRatio(1f)
            .animateContentSize()
            .border(1.dp, Color.Black)
            .clickable {
                setSelectImage(galleryImage)
            }
    Box {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(galleryImage.uri)
                .crossfade(true)
                .build(),
            loading = {
                ListCircularProgressIndicator(0.2f)
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = selectedModifier
        )

        if (isSelected) {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp, end = 10.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.main_color))
                    .align(Alignment.TopEnd)
            )
        }
    }
}