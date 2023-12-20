package com.android.myfooddiarybookaos.login.navi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.login.SplashScreen
import com.android.myfooddiarybookaos.login.mainUi.InsertScreen
import com.android.myfooddiarybookaos.login.passUi.FindPassScreen
import com.android.myfooddiarybookaos.login.mainUi.LoginScreen
import com.android.myfooddiarybookaos.login.passUi.SetNewPassword

@Composable
fun NavigationGraph(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LoginScreenRoot.SPLASH
    ){
        composable(LoginScreenRoot.SPLASH){ SplashScreen(navController)}
        composable(LoginScreenRoot.LOGIN_MAIN){ LoginScreen(navController) }
        composable(LoginScreenRoot.FIND_PASSWORD){ FindPassScreen(navController) }
        composable(LoginScreenRoot.NEW_PASSWORD){ SetNewPassword(navController = navController, realPass = "sample")}
        composable(LoginScreenRoot.INSERT){ InsertScreen(navController = navController)}
    }

}