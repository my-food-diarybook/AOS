package com.android.myfooddiarybookaos.home.component


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

private const val DAY_OF_WEAK = 7

@Composable
fun CalendarLayout(
    viewUpdate: MutableState<Boolean>
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 91.dp
        )
    ) {
        // 요일 뷰
        val dayList = listOf("S", "M", "T", "W", "T", "F", "S")
        LazyVerticalGrid(
            columns = GridCells.Fixed(DAY_OF_WEAK),
            content = {
                items(DAY_OF_WEAK) { index ->
                    DayLayer(text = dayList[index])
                }
            }
        )
        Spacer(modifier = Modifier.height(21.85.dp))
        // 캘린더
        MonthDataView(viewUpdate)
    }
}


// 일 별 레이어
@Composable
private fun DayLayer(text: String) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(
                horizontal = 12.86.dp
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 12.scaledSp(),
            fontFamily = robotoRegular,
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.color_day_of_weak),
        )
    }
}

