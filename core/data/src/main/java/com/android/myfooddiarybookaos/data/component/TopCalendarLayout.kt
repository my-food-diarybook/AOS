package com.android.myfooddiarybookaos.data.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.utils.scaledSp
import java.util.*


@SuppressLint("UnrememberedMutableState")
@Composable
fun TopCalendarLayout(
    todayViewModel: TodayViewModel = hiltViewModel(),
    resetData: () -> Unit,
    isMainView: Boolean
) {
    var isTopLayoutClick by remember { // 캘린더 클릭 여부
        mutableStateOf(false)
    }

    val textState = remember {
        mutableStateOf(
            todayViewModel.getCurrentCalendarInfo()
        )
    }

    if (isTopLayoutClick) { // 캘린더 클릭 동작
        todayViewModel.getCurrentCalendar().apply {
            // dialog 생성
            SelectCalendarDialog(
                isTopLayoutClick = {// 캘린더 픽 전달 받기
                    isTopLayoutClick = it
                },
                changeAllData = {
                    resetData()
                    textState.value = todayViewModel.getCurrentCalendarInfo()
                }
            )
        }

    }


    Column(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 24.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable(onClick = { isTopLayoutClick = true }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextBox(
                    text = textState.value,
                    fontWeight = 700,
                    fontFamily = robotoBold,
                    fontSize = 34.scaledSp(),
                    color = colorResource(id = R.color.black),
                    lineHeight = 34.scaledSp()
                )
                Spacer(modifier = Modifier.width(1.59.dp))
                Box(
                    modifier = Modifier.size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.keyboard_arrow_down),
                        contentDescription = "",
                    )
                }
            }

            if (isMainView) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                todayViewModel.setPrevDate()
                                textState.value = todayViewModel.getCurrentCalendarInfo()
                                resetData()
                            }
                            .size(34.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.chevron_left_24px),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .clickable {
                                todayViewModel.setNextDate()
                                textState.value = todayViewModel.getCurrentCalendarInfo()
                                resetData()
                            }
                            .size(34.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.chevron_right_24px),
                            contentDescription = null
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        Divider(
            modifier = Modifier
                .height(2.dp)
                .coloredInnerShadow(
                    color = colorResource(id = R.color.black_10),
                    offsetY = 1.dp,
                    blurRadius = 4.dp
                )
        )
    }
}
