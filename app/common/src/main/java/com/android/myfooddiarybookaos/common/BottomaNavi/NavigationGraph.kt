package com.android.myfooddiarybookaos.common.BottomaNavi

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.android.myfooddiarybookaos.TabMyAccount.MyScreen
import com.android.myfooddiarybookaos.TabSearch.SearchScreen
import com.android.myfooddiarybookaos.TabTimeLine.TimeLineScreen

import com.android.myfooddiarybookaos.home.HomeScreen

// NavController : 대상을 이동 시키는 요소 
@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute
    ){
        composable(BottomNavItem.Home.screenRoute){
            HomeScreen()
        }
        composable(BottomNavItem.TimeLine.screenRoute){
            TimeLineScreen()
        }
        composable(BottomNavItem.Search.screenRoute){
            SearchScreen()
        }
        composable(BottomNavItem.MyAccount.screenRoute){
            MyScreen()
        }
    }

}