package com.android.myfooddiarybookaos.myaccount.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoBold
import java.util.*

@Composable
fun NoticeScreen() {

    val isBackState = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(
                        start = 4.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clickable(onClick = { isBackState.value = true }),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chevron_left_24px),
                        contentDescription = "",
                    )
                }

                TextBox(
                    text = "공지사항",
                    fontWeight = 500,
                    fontFamily = robotoBold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.black)
                )

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
}