package com.android.myfooddiarybookaos.timeline.item

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.model.timeLine.TimeLine

@Composable
fun TimeLineItem(
    timeLine: TimeLine,
    images: List<String>,
    screenWidth: Dp
) {
    val dataSize = timeLine.diaryList.size
    val imageSize = remember { mutableStateOf(getImageDp(dataSize, screenWidth)) }

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        items(images) { image ->
            ImageItem(
                bytes = image,
                imageSize = imageSize.value,
                onClick = {
                    // go data
                }
            )
        }
    }

}

fun getImageDp(
    count: Int,
    screenWidth: Dp
): Dp {
    return if (count > 4) (screenWidth / 360) * 80
    else screenWidth / count
}