package com.android.myfooddiarybookaos.detail.memoUi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun MemoTopLayer(
    backStage: () -> Unit,
    nextStage: () -> Unit,
    memoTopColorState: State<Color>
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
                fontSize = 18.scaledSp(),
                color = memoTopColorState.value,
                fontWeight = FontWeight.W500,
                fontFamily = robotoRegular,
                lineHeight = 18.scaledSp()
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
