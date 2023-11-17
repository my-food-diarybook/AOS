package com.android.myfooddiarybookaos.detail.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.data.state.ApplicationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import com.android.myfooddiarybookaos.core.data.R
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.component.*
import com.android.myfooddiarybookaos.detail.imageSlider.ImageSliderScreen
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel


@Composable
fun DetailScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    detailViewModel: DetailViewModel = hiltViewModel(),
    todayViewModel: TodayViewModel = hiltViewModel()
) {
    BackHandler(enabled = true, onBack = {
        detailViewModel.goBack()
    })

    val diaryDetail = detailViewModel.diaryDetail.observeAsState().value
    val topDate = todayViewModel.getTopDate(diaryDetail?.date)
    LaunchedEffect(Unit) {
        detailViewModel.initAppState(appState,diaryState)
        detailViewModel.setDiaryDetail()
    }

    Column {
        DetailTopLayer(topDate)

        ImageSliderScreen(diaryDetail?.images)

        Surface(
            modifier = Modifier.padding(start = 21.dp, top = 25.dp)
        ) {
            DetailMenuTime(diaryDetail = diaryDetail)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 19.dp, end = 11.dp)
                .clickable {
//                    initMemo()
                }
        ){
            DetailMemo(diaryDetail)
            Spacer(modifier = Modifier.height(15.dp))
            DetailLocation(diaryDetail)
            Spacer(modifier = Modifier.height(15.dp))
            DetailTag(diaryDetail)
        }

    }
}