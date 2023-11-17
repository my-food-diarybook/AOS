package com.android.myfooddiarybookaos.detail.ui

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
import com.android.myfooddiarybookaos.data.function.diaryTimeData
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.component.*
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel

@Composable
fun DetailScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    BackHandler(enabled = true, onBack = {
        detailViewModel.goBack()
    })
    val diaryDetail = detailViewModel.diaryDetail.observeAsState().value

    LaunchedEffect(Unit) {
        detailViewModel.initAppState(appState,diaryState)
        detailViewModel.setDiaryDetail()
    }

    Column {
        DetailTopLayer()

        Image(
            // 여기 넘기는 페이지 처리 해야함
            rememberAsyncImagePainter(diaryDetail?.images?.firstOrNull()?.bytes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(499.dp),
            contentScale = ContentScale.Crop,
        )

        Surface(
            modifier = Modifier.padding(start = 21.dp, top = 25.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        1.dp,
                        colorResource(id = R.color.main_color),
                        RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                diaryDetail?.diaryTime?.let { timeData ->
                    Text(
                        color = colorResource(id = R.color.main_color),
                        modifier = Modifier.padding(
                            start = 9.dp, end = 8.dp,
                            top = 5.dp, bottom = 5.dp
                        ),
                        text = diaryTimeData(timeData),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W100))
                    )
                }
            }
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
            Spacer(modifier = Modifier.height(22.dp))
            DetailLocation(diaryDetail)
            Spacer(modifier = Modifier.height(22.dp))
            DetailTag(diaryDetail)
        }

    }
}