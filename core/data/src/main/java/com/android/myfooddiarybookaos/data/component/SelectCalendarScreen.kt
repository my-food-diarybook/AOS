package com.android.myfooddiarybookaos.data.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.utils.HomeUtils
import com.android.myfooddiarybookaos.data.utils.scaledSp

import com.android.myfooddiarybookaos.model.MonthDate

import java.util.*



@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectCalendarScreen(
    todayViewModel: TodayViewModel = hiltViewModel(),
    isTopLayoutClick: (Boolean) -> Unit
) {
    // 오늘 데이터
    val todayYear by remember {
        mutableStateOf(todayViewModel.getTodayCalendar().get(Calendar.YEAR))
    }
    val todayDate by remember {
        mutableStateOf(todayYear*12+todayViewModel.getTodayCalendar().get(Calendar.MONTH)+1)
    }
    // 현재 선택 데이터
    val currentDate by remember {
        mutableStateOf(todayViewModel.getCurrentDate())
    }
    // 현재 뷰어
    var currentYear by remember { mutableStateOf( //선택 년도
        todayViewModel.getCurrentCalendar().get(Calendar.YEAR)
    ) }
    // month data 갱신
    val monthList = List(HomeUtils.MAX_MONTH) { i ->
        MonthDate(
            i + 1,
            currentYear * 12 + i + 1
        )
    } // 리스트 초기화
    // color left
    val rightColorState by animateColorAsState(
        if (currentYear+1 <= todayYear) colorResource(id = R.color.black)
        else colorResource(id = R.color.calender_next_color)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clickable(onClick = {
                        currentYear -= 1
                    }),
                contentAlignment = Alignment.Center
            ){
                Image(painterResource(id = R.drawable.left_side), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(41.dp))
            TextBox(
                text = "$currentYear",600,
                robotoBold,
                36.scaledSp(),
                colorResource(id = R.color.black),
                lineHeight = 18.scaledSp()
            )
            Spacer(modifier = Modifier.width(41.dp))
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clickable(onClick = {
                        // 클릭 조건 (지난 달)
                        if (currentYear + 1 <= todayYear) currentYear += 1
                    }),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.right_side),
                    contentDescription = null,
                    // 클릭 색상 변경
                    colorFilter = ColorFilter.tint(rightColorState)
                )
            }
        }

        Spacer(modifier = Modifier.height(21.5.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = 14.5.dp
                )
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                content = {
                    items(HomeUtils.MAX_MONTH) { index ->
                        ItemMonth(month = monthList[index],todayDate,currentDate,
                            selectMonth = { // month 클릭
                                // 클릭 시 다이어로그 닫기
                                isTopLayoutClick(false)
                                // 뷰 모델 수정
                                todayViewModel.setCurrentDate(currentYear,it-1)
                            }
                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(22.5.dp))
    }
}

@Composable
private fun ItemMonth(
    month : MonthDate, todayDate:Int, currentDate:Int,
    selectMonth : (Int) -> Unit
){
    val textViewColor by animateColorAsState(
        if (todayDate < month.isSelected) colorResource(id = R.color.line_color_deep)
        else if (currentDate==month.isSelected) colorResource(id = R.color.main_color)
        else colorResource(id = R.color.black)

    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(
                top = 10.5.dp,
                bottom = 10.5.dp
            )
            .clickable(onClick = {
                // 클릭 조건 (지난 달)
                if (todayDate >= month.isSelected) selectMonth(month.month)
            }),
    ){
        TextBox(
            text = month.month.toString() + "월",
            700,
            robotoBold,
            20.scaledSp(),
            color = textViewColor,
            lineHeight = 18.scaledSp()
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSelectCalendar(){
    SelectCalendarScreen(
        hiltViewModel(),
        isTopLayoutClick = { }
    )
}