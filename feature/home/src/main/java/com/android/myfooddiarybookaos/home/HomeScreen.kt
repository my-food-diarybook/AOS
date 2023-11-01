package com.android.myfooddiarybookaos.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.myfooddiarybookaos.home.calendar.CalendarLayout
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout

import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModel

@Composable
fun HomeScreen(
    todayViewModel: TodayViewModel = viewModel()
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
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
