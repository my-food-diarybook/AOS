package com.android.myfooddiarybookaos.timeline.item

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageItem(
    imageBitmap: MutableState<Bitmap>,
    imageSize: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(imageSize)
            .clickable { onClick() }
    ) {
        Image(
            rememberAsyncImagePainter(imageBitmap.value),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}