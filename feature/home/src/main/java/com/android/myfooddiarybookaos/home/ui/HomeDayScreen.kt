package com.android.myfooddiarybookaos.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.state.AddScreenState
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

    LaunchedEffect(Unit) {
        homeViewModel.initState(appState, diaryState)
    }

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        backStage(diaryState, appState)
    })

    val homeDays = homeViewModel.homeDayInDiary.collectAsState().value.homeDayList
    val viewUpdate = rememberSaveable { mutableStateOf(true) }
    val currentDate = diaryState.currentHomeDay.value
    if(viewUpdate.value) {
        LaunchedEffect(Unit) {
            homeViewModel.getHomeDayInDiary(currentDate)
            viewUpdate.value = false
        }
    }

    // 업로드 시도
    if (diaryState.isSelectedGallery.value) {
        if (diaryState.addScreenState.value == AddScreenState.ADD_HOME_DAY) {
            homeViewModel.makeNewDiary(
                currentDate,
                diaryState.multiPartList,
                diaryState = { isUpdate ->
                    if (isUpdate) {
                        homeViewModel.getHomeDayInDiary(currentDate)
                    }
                }
            )
            diaryState.resetSelectedInfo()
        }
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
        ) {
            todayViewModel.apply {
                HomeDayTopLayer(
                    currentDate = getTopDate(currentDate),
                    prevDate = getTopDate(homeViewModel.getPrevHomeDay()),
                    nextDate = getTopDate(homeViewModel.getNextHomeDay()),
                    onChange = {
                        diaryState.currentHomeDay.value  = it
                        viewUpdate.value = true
                    },
                )
            }
        }

        // item 상, 하로 8dp, 첫 item 상 (17dp - 8dp = 9dp)
        Spacer(modifier = Modifier.height(9.dp))

        //여기 아이템 리스트 추가
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp),
            state = rememberLazyListState()
        ) {
            items(homeDays.size) { index ->
                ItemHomeDay(
                    homeDay = homeDays[index],
                    clickDiary = {
                        homeViewModel.goDetailView(homeDays[index].id)
                    }
                )
            }
        }
    }

}

fun backStage(diaryState: DiaryState, appState: ApplicationState) {
    diaryState.resetHomeDay()
    appState.navController.popBackStack()
}