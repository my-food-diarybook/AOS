package com.android.myfooddiarybookaos.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.myfooddiarybookaos.home.calendar.CalendarLayout
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout

import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.data.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun HomeScreen(
    todayViewModel: TodayViewModel = viewModel()
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
//        // 임시 로그인
//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .wrapContentWidth()
//                .wrapContentHeight(),
//
//        ) {
//            Text("로그인 ui 보기(test)")
//        }

        // top calendar
        TopCalendarLayout(todayViewModel)

        // mid calendar
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CalendarLayout()
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}