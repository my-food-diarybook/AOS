package com.android.myfooddiarybookaos.data.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun ErrorPage(
    load: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 53.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "현재 오류가 발생했습니다.\n" +
                    "다시 시도해 주세요. ",
            color = Color.Black,
            fontWeight = FontWeight.W300,
            fontFamily = robotoRegular,
            fontSize = 14.scaledSp(),
            lineHeight = 20.scaledSp(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
                .clickable {
                    load()
                }
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.main_color),
                        shape = RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "다시 시도",
                    color = Color.White,
                    fontWeight = FontWeight.W700,
                    fontFamily = robotoRegular,
                    fontSize = 16.scaledSp(),
                    lineHeight = 18.scaledSp(),
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp,
                            vertical = 10.5.dp
                        )
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewErr(
) {
    ErrorPage {

    }
}
