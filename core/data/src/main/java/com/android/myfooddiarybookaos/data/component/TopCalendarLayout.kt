package com.android.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.Dialog.SelectCalendarDialog
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.robotoBold
import java.util.*



@SuppressLint("UnrememberedMutableState")
@Composable
fun TopCalendarLayout(
    todayViewModel : TodayViewModel = hiltViewModel()
){
    var isTopLayoutClick  by remember{ // 캘린더 클릭 여부
        mutableStateOf(false)
    }

    // currentCalendar observe
    val topTexting = todayViewModel.todayRepository
        .currentCalendar.observeAsState().value!!

    if (isTopLayoutClick){ // 캘린더 클릭 동작
        todayViewModel.todayRepository.currentCalendar.value?.apply {
            // dialog 생성
            SelectCalendarDialog(
                todayViewModel,
                isTopLayoutClick = {// 캘린더 픽 전달 받기
                    isTopLayoutClick = it
                }
            )
        }

    }


    Column(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ){
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = 20.dp
                )
                .clickable(onClick = { isTopLayoutClick = true }),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextBox(
                text =   "${topTexting.get(Calendar.YEAR)}" +
                        ".${topTexting.get(Calendar.MONTH).plus(1)}",
                fontWeight = 700,
                fontFamily = robotoBold,
                fontSize = 34.sp,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.width(1.59.dp))
            Box(
                modifier = Modifier.size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.downarrow),
                    contentDescription = "",
                )
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        Divider(modifier = Modifier
            .height(2.dp)
            .coloredInnerShadow(
                color = colorResource(id = R.color.black_10),
                offsetY = 1.dp,
                blurRadius = 4.dp
            )
        )
    }
}

