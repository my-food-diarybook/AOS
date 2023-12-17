package com.android.myfooddiarybookaos.detail.mainUi.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.model.image.Image

@Composable
fun OneDetailImage(
    image: Image
) {
    val imageModel = remember {byteStringToBitmap(image.bytes) }

    AsyncImage(
        model = imageModel,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}