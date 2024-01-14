package com.android.myfooddiarybookaos.data.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BoxScope.ToastMessaging(
    message: String,
    removeView: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .align(Alignment.BottomCenter)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.toast_back),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Text(
                text = message,
                fontWeight = FontWeight.W400,
                fontFamily = robotoRegular,
                fontSize = 15.scaledSp(),
                lineHeight = 20.scaledSp(),
                letterSpacing = (-0.4).sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 14.dp,
                        end = 7.dp,
                        top = 14.dp,
                        bottom = 12.dp
                    )
            )
            rememberCoroutineScope().launch {
                delay(1000)
                removeView()
            }
        }
    }
}