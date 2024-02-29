package com.android.myfooddiarybookaos.detail.memoUi.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun DiaryTimeData(
    diaryTime: DiaryTime,
    click: () -> Unit,
    currentSelect: MutableState<String>,
) {

    if (currentSelect.value == diaryTime.name) {
        Box(
            modifier = Modifier
                .height(28.dp)
                .width(54.dp)
                .clickable {
                    click()
                }
                .border(
                    1.dp,
                    colorResource(id = R.color.main_color),
                    RoundedCornerShape(4.dp),
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = diaryTime.getNameCode(),
                fontSize = 20.scaledSp(),
                fontFamily = robotoRegular,
                fontWeight = FontWeight.W100,
                color = colorResource(id = R.color.main_color),
                lineHeight = 18.scaledSp(),
            )
        }
    } else {
        Box(
            modifier = Modifier
                .height(28.dp)
                .width(54.dp)
                .clickable {
                    click()
                }
                .background(
                    colorResource(id = R.color.back_D9D9D9),
                    RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = diaryTime.getNameCode(),
                fontSize = 20.scaledSp(),
                fontFamily = robotoRegular,
                fontWeight = FontWeight.W100,
                color = colorResource(id = R.color.line_color_deep),
                lineHeight = 18.scaledSp(),
            )
        }
    }
}
