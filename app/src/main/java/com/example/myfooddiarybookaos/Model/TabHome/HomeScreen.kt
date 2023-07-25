package com.example.myfooddiarybookaos.Model.TabHome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfooddiarybookaos.Layout.CalendarLayout
import com.example.myfooddiarybookaos.Layout.TopCalendarLayout
import java.util.*
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.ViewModel.TodayViewModel

@Composable
fun HomeScreen(
    todayViewModel : TodayViewModel
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
        todayViewModel.todayCalendar.value?.let { TopCalendarLayout(it) }
        // mid calendar
        Box(
            Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.size_15),
                    end = dimensionResource(id = R.dimen.size_15)
                )
            ,
            contentAlignment = Alignment.Center
        ) {
            todayViewModel.customCalendar.value?.let { CalendarLayout(it)}
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
