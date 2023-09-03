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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Dialog.SelectCalendarDialog
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredShadow
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.viewModel.TodayViewModelInterface
import java.util.*



@SuppressLint("UnrememberedMutableState")
@Composable
fun TopCalendarLayout(
    todayViewModel : TodayViewModelInterface,
){
    var isTopLayoutClick  by remember{ // 캘린더 클릭 여부
        mutableStateOf(false)
    }
    // currentCalendar observe
    val topTexting = todayViewModel.currentCalendar.observeAsState().value!!

    if (isTopLayoutClick){ // 캘린더 클릭 동작
        todayViewModel.currentCalendar.value?.apply {
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
            .height(dimensionResource(id = R.dimen.size_88))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ){
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = dimensionResource(id = R.dimen.size_20)
                )
                .clickable(onClick = { isTopLayoutClick = true }),
            verticalAlignment = Alignment.Top
        ){
            TextBox(
                text =   "${topTexting.get(Calendar.YEAR)}" +
                        ".${topTexting.get(Calendar.MONTH).plus(1)}",
                fontWeight = 700,
                fontFamily = robotoBold,
                fontSize = dimensionResource(id = R.dimen.size_34_sp).value.sp,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_1_59)))
            Box(
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_40)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.downarrow),
                    contentDescription = "",
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_10)))

        Divider(modifier = Modifier
            .height(2.dp)
            .coloredShadow(
                color = colorResource(id = R.color.black_10),
                offsetY = 1.dp,
                blurRadius = 4.dp
            )
        )
    }
}

