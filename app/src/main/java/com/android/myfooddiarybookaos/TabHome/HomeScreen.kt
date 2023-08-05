package com.android.myfooddiarybookaos.TabHome

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.myfooddiarybookaos.Layout.CalendarLayout
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout
import com.android.myfooddiarybookaos.R
import com.android.myfooddiarybookaos.ViewModel.TodayViewModelInterface

@Composable
fun HomeScreen(
    todayViewModel: TodayViewModelInterface
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
            Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.size_15),
                    end = dimensionResource(id = R.dimen.size_15)
                ),
            contentAlignment = Alignment.Center
        ) {
            CalendarLayout(todayViewModel)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
