package com.android.myfooddiarybookaos.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel

@Composable
fun DetailTopLayer(
    detailViewModel: DetailViewModel = hiltViewModel(),
    todayViewModel: TodayViewModel = hiltViewModel()
) {
    Box(
        Modifier
            .height(90.dp)
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .padding(start = 5.dp, bottom = 2.dp)
                .align(Alignment.BottomStart)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clickable {
                        detailViewModel.goBack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_left),
                    contentDescription = null
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.75.dp),
            text = todayViewModel.getTopDate(detailViewModel.diaryDetail.value?.date),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.sp
        )

        Surface(
            modifier = Modifier
                .padding(end = 5.dp, bottom = 2.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.more_vert_24px),
                    contentDescription = null
                )
            }
        }


    }
}