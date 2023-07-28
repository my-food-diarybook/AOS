package com.example.myfooddiarybookaos.Dialog

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.myfooddiarybookaos.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myfooddiarybookaos.Model.MonthDate
import com.example.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import com.example.myfooddiarybookaos.ui.theme.TextBox
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
    val monthList = List(MAX_MONTH) { i -> MonthDate(i + 1,currentYear*12+i+1) } // 리스트 초기화
    // color left
    val rightColorState by animateColorAsState(
        if (currentYear+1 <= todayYear) colorResource(id = R.color.black)
        else colorResource(id = R.color.calender_next_color)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.size_369))
            .height(dimensionResource(id = R.dimen.size_267))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_23_5)))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painterResource(id = R.drawable.left_side), contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_29))
                    .width(dimensionResource(id = R.dimen.size_18))
                    .clickable(onClick = {
                        currentYear -= 1
                    }),

            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_61_76)))
            TextBox(
                text = "$currentYear",600,
                Font(R.font.roboto_bold),
                dimensionResource(id = R.dimen.size_36_sp).value.sp,
                colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_61_76)))
            Image(
                painter = painterResource(id = R.drawable.right_side),
                contentDescription = null,
                colorFilter = ColorFilter.tint(rightColorState),
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_29))
                    .width(dimensionResource(id = R.dimen.size_18))
                    .clickable(onClick = {
                        // 클릭 조건 (지난 달)
                        if (currentYear+1 <= todayYear) currentYear += 1
                    }),
            )
        }

        
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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
                                todayViewModel.setCustomCalendar()
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun ItemMonth(
    month : MonthDate,todayDate:Int,currentDate:Int,
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
            Font(R.font.roboto_regular),
            dimensionResource(id = R.dimen.size_20_sp).value.sp,
            color = textViewColor
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSelectCalendar(){
    SelectCalendarScreen(FakeTodayViewModel(), isTopLayoutClick = { true })
}