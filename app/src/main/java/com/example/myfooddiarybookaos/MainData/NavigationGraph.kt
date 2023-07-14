package com.example.myfooddiarybookaos.MainData

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfooddiarybookaos.TabHome.HomeScreen
import com.example.myfooddiarybookaos.TabMyAccount.MyScreen
import com.example.myfooddiarybookaos.TabSearch.SearchScreen
import com.example.myfooddiarybookaos.TabTimeLine.TimeLineScreen

// NavController : 대상을 이동 시키는 요소 
@Composable
fun NavigationGraph(navController: NavHostController) {
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