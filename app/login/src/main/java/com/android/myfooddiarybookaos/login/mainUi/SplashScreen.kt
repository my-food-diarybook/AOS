package com.android.myfooddiarybookaos.login

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.android.myfooddiarybookaos.core.data.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        delay(1000)
        navController.navigate("loginScreen")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.main_color)),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.bc_bacground),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
