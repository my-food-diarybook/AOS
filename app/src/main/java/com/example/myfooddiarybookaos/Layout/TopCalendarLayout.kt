package com.example.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.myfooddiarybookaos.Dialog.SelectCalendarDialog
import com.example.myfooddiarybookaos.Dialog.SelectCalenderFragment
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import com.example.myfooddiarybookaos.ui.theme.TextBox
import java.util.*



@SuppressLint("UnrememberedMutableState")
@Composable
fun TopCalendarLayout(todayViewModel : TodayViewModelInterface){
    var isTopLayoutClick  by remember{ // 캘린더 클릭 여부
        mutableStateOf(false)
    }
    val topText by remember {
        if (todayViewModel.currentCalendar.value!=null){
            val today = todayViewModel.currentCalendar.value!!
            mutableStateOf(
            "${today.get(Calendar.YEAR)}" +
                    ".${today.get(Calendar.MONTH).plus(1)}"
            )
        }else{
            mutableStateOf("")
        }
    }
    if (isTopLayoutClick){ // 캘린더 클릭 동작
        todayViewModel.todayCalendar.value?.apply {
            SelectCalendarDialog(
                todayViewModel,
                isTopLayoutClick = {// 캘린더 픽 전달 받기
                    isTopLayoutClick = it
                }
            )
        }

    }
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(
                top = dimensionResource(id = R.dimen.size_47),
                start = dimensionResource(id = R.dimen.size_20),
                bottom = dimensionResource(id = R.dimen.size_11_59)
            )
            .clickable(onClick = { isTopLayoutClick = true }),
        verticalAlignment = Alignment.Bottom,

    ){

        TextBox(
            text =  topText,
            fontWeight = 700,
            fontFamily = Font(R.font.roboto_bold),
            fontSize = dimensionResource(id = R.dimen.size_34).value.sp,
            color = colorResource(id = R.color.black) )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_11_59)))
        Image(
            painter = painterResource(id = R.drawable.downarrow),
            contentDescription = "",
            Modifier
                .padding(bottom = dimensionResource(id = R.dimen.size_13))
                .height(dimensionResource(id = R.dimen.size_14))
                .width(dimensionResource(id = R.dimen.size_20))
            ,

        )
    }
    Divider(
        color = colorResource(id = R.color.line_color),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.size_1))
            .fillMaxWidth()
    )
}
