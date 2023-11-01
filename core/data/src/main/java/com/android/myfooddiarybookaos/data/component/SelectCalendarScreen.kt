package com.android.myfooddiarybookaos.Dialog

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.todayViewModel.FakeTodayViewModel

import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import com.android.myfooddiarybookaos.model.MonthDate

import java.util.*


// SelectCalendar Dialog
private const val MAX_MONTH = 12

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectCalendarScreen(
    todayViewModel: TodayViewModelInterface,
    isTopLayoutClick: (Boolean) -> Unit
) {
    // 오늘 데이터
    val todayYear = todayViewModel.todayCalendar.value!!.get(Calendar.YEAR)
    val todayDate = todayYear*12+ todayViewModel.todayCalendar.value!!.get(Calendar.MONTH)+1 //현재 달
    // 현재 선택 데이터
    val currentDate = todayViewModel.currentCalendar.value!!.get(Calendar.YEAR)*12+
            todayViewModel.currentCalendar.value!!.get(Calendar.MONTH)+1 //현재 달
    // 현재 뷰어
    var currentYear by remember { mutableStateOf( //선택 년도
        todayViewModel.currentCalendar.value!!.get(Calendar.YEAR)
    ) }
    // month data 갱신
    val monthList = List(MAX_MONTH) { i ->
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
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_9)))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_58))
                    .clickable(onClick = {
                        currentYear -= 1
                    }),
                contentAlignment = Alignment.Center
            ){
                Image(painterResource(id = R.drawable.left_side), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_41)))
            TextBox(
                text = "$currentYear",600,
                robotoBold,
                36.sp,
                colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_41)))
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_58))
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

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_21_5)))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.size_14_5)
                )
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                content = {
                    items(MAX_MONTH) { index ->
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
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_22_5)))
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
                top = dimensionResource(id = R.dimen.size_10_5),
                bottom = dimensionResource(id = R.dimen.size_10_5)
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
            20.sp,
            color = textViewColor
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSelectCalendar(){
    SelectCalendarScreen(FakeTodayViewModel(), isTopLayoutClick = { })
}