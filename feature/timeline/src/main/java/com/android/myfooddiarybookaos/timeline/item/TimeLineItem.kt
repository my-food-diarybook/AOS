package com.android.myfooddiarybookaos.timeline.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.model.timeLine.TimeLine

@Composable
fun TimeLineItem(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLine: TimeLine,
    screenWidth: Dp
) {

    val dataSize = timeLine.diaryList.size
    val imageSize = remember { mutableStateOf(getImageDp(dataSize, screenWidth)) }

    val currentFont =
        if (todayViewModel.getTodayDate() == timeLine.date)
            FontFamily(Font(R.font.roboto_regular, FontWeight.W700)
        )
        else FontFamily(Font(R.font.roboto_regular, FontWeight.W400))

    Column {
        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 2.dp, top = 12.dp),
            text = todayViewModel.getTopDate(timeLine.date),
            fontFamily = currentFont,
            color = Color.Black
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(134.dp),
            state = rememberLazyListState()
        ) {
            items(timeLine.diaryList) { diary ->
                ImageItem(
                    bytes = diary.bytes,
                    imageSize = imageSize.value,
                    onClick = {
                        // go data
                    }
                )
            }
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