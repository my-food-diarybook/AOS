package com.example.myfooddiarybookaos.TabHome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfooddiarybookaos.Layout.CalendarLayout
import com.example.myfooddiarybookaos.Layout.TopCalendarLayout

@Composable
fun HomeScreen(){
    Column{
        // 임시 로그인
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),

        ) {
            Text("로그인 ui 보기(test)")
        }

        TopCalendarLayout()
        CalendarLayout()

    }
}



