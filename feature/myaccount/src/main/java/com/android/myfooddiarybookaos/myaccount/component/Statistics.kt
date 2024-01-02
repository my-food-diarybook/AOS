package com.android.myfooddiarybookaos.myaccount.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.model.statistics.Statistics
import com.android.myfooddiarybookaos.myaccount.viewModel.MyViewModel

@Composable
fun Statistics(
    viewModel: MyViewModel = hiltViewModel()
) {

    val userStatistics = viewModel.userStatistics.collectAsState()

    Box(
        modifier = Modifier
            .padding(
                top = 3.dp,
                bottom = 11.dp
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.line_color_deep),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = 9.dp,
                end = 11.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(19.dp))
            TextBox(
                text = "모든 식사 일기",
                fontWeight = 400,
                fontFamily = robotoRegular,
                fontSize = 16.scaledSp(),
                color = colorResource(id = R.color.black),
                lineHeight = 16.scaledSp(),
            )
            TextBox(
                text = userStatistics.value.totalCount.toString(),
                fontWeight = 700,
                fontFamily = robotoBold,
                fontSize = 28.scaledSp(),
                color = colorResource(id = R.color.main_color),
                lineHeight = 28.scaledSp(),
            )
            Spacer(modifier = Modifier.height(12.dp))
            InDivider()
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("아침", "아점", "점심", "점저")) {
                    CategoryMenu(category, viewModel.getDiaryCount(category))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            InDivider()
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("간식", "저녁", "야식", "기타")) {
                    viewModel.getDiaryCount(category)
                    CategoryMenu(category, viewModel.getDiaryCount(category))
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
        }
    }


}

@Composable
private fun InDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = colorResource(id = R.color.line_color_deep)
    )
}
