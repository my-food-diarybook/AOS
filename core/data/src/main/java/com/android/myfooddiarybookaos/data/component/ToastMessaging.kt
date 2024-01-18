package com.android.myfooddiarybookaos.data.component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ToastMessaging(
    message: String,
    removeView: () -> Unit
) {
    val snackState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    scope.launch {
        snackState.showSnackbar(message)
        delay(1500)
        removeView()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SnackbarHost(
            hostState = snackState,
            modifier = Modifier.align(Alignment.BottomCenter),
        ) { snackbarData: SnackbarData ->
            Surface(
                modifier = Modifier.padding(20.dp)
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
                        text = snackbarData.message,
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
                }
            }
        }
    }
}
