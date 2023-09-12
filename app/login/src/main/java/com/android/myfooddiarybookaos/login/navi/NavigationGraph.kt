package com.android.myfooddiarybookaos.login.navi

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.login.SplashScreen
import com.android.myfooddiarybookaos.login.findPassUi.FindPassScreen
import com.android.myfooddiarybookaos.login.mainUi.LoginScreen

@Composable
fun NavigationGraph(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ){
        composable("splashScreen"){ SplashScreen(navController)}
        composable("loginScreen"){ LoginScreen(navController) }
        composable("findPassScreen"){ FindPassScreen(navController) }
    }

}