package com.android.myfooddiarybookaos.timeline.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap

@Composable
fun ImageItem(
    bytes: String,
    imageSize: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(imageSize)
            .fillMaxHeight()
            .clickable { onClick() }
    ) {
        Image(
            rememberAsyncImagePainter(byteStringToBitmap(bytes)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}