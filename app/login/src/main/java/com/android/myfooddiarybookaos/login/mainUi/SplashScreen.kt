package com.android.myfooddiarybookaos.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.login.viewModel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val state = loginViewModel.state.collectAsState()
    val context = LocalContext.current

    fun goMain(
        context: Context,
    ) {
        val intent = Intent(
            context,
            Class.forName("com.android.myfooddiarybookaos.MainActivity")
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun loginFlow(isLogin: Boolean){
        if (isLogin) {
            goMain(context)
        } else {
            navController.navigate("loginScreen")
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            delay(500L)
            loginViewModel.isLogin {
                loginFlow(it)
            }
        }
    }

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.main_color)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bc_bacground),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
