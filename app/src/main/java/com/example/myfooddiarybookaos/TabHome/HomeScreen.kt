package com.example.myfooddiarybookaos.TabHome

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

@Composable
fun HomeScreen() {
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

        // 캘린더 초기화
        val calendarDate = Calendar.getInstance()
        val customCalendar = CustomCalendar(calendarDate.time)
        customCalendar.initBaseCalendar()

        TopCalendarLayout(
            "${calendarDate.get(Calendar.YEAR)}" +
                    ".${
                        calendarDate.get(Calendar.MONTH)
                            .plus(1)
                    }"
        )
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
            CalendarLayout(customCalendar)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
