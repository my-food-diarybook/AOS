package com.android.myfooddiarybookaos.home.navi

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel

@Composable
fun HomeNavi(
    navController: NavHostController,

){
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

    }
}