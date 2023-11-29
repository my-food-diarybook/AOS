package com.android.myfooddiarybookaos.detail.memoUi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun MemoTopLayer(
    backStage: ()-> Unit,
    nextStage: ()-> Unit
) {
    Spacer(modifier = Modifier.height(43.dp))
    Box(
        modifier = Modifier
            .height(44.dp)
            .padding(start = 5.dp, end = 14.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clickable {
                    backStage()
                }
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.close),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .size(44.dp)
                .clickable {
                    nextStage()
                }
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "완료",
                fontSize = 18.sp,
                color = colorResource(id = R.color.line_color_deep),
                fontWeight = FontWeight.W500,
                fontFamily = robotoRegular
            )
        }
    }
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