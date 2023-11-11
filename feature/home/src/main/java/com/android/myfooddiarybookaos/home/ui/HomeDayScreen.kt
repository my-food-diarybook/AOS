package com.android.myfooddiarybookaos.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import androidx.compose.material.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Layout.MonthDataView
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.home.component.HomeDayTopLayer
import com.android.myfooddiarybookaos.home.item.ItemHomeDay
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel

@Composable
fun HomeDayScreen(
    diaryState: DiaryState,
    appState: ApplicationState,
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    diaryState.isHomeDay.value = true
    val currentDate = diaryState.currentHomeDay.value
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        backStage(diaryState, appState)
    })

    LaunchedEffect(Unit) {
        homeViewModel.getHomeDayInDiary(currentDate)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(start = 10.dp, bottom = 9.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clickable {
                        backStage(diaryState, appState)
                    }
                    .align(Alignment.BottomStart),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_left),
                    contentDescription = null
                )
            }
        }
        Divider(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .coloredInnerShadow(
                    color = colorResource(id = R.color.black_10),
                    offsetY = 1.dp,
                    blurRadius = 4.dp
                )
        )

        Spacer(modifier = Modifier.height(6.dp))

        // 박스 (좌우)
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)) {
            homeViewModel.homeDayInDiary.value?.let {
                HomeDayTopLayer(
                    currentDate = todayViewModel.getTopDate(currentDate),
                    prevDate = todayViewModel.getTopDate(it.beforeDay),
                    nextDate = todayViewModel.getTopDate(it.afterday)
                )
            }
        }

        // item 상, 하로 8dp, 첫 item 상 (17dp - 8dp = 9dp)
        Spacer(modifier = Modifier.height(9.dp))

        //여기 아이템 리스트 추가
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 20.dp
            ),
            state = rememberLazyListState()
        ) {
            homeViewModel.homeDayInDiary.value?.homeDayList?.let { homeDays ->
                items(homeDays) { homeDay ->
                    ItemHomeDay(homeDay = homeDay)
                }
            }
        }
    }

    fun backStage(){
        diaryState.currentHomeDay.value = ""
        diaryState.isHomeDay.value = false
        appState.navController.popBackStack()
    }
}

fun backStage(diaryState: DiaryState,appState: ApplicationState){
    diaryState.currentHomeDay.value = ""
    diaryState.isHomeDay.value = false
    appState.navController.popBackStack()
}