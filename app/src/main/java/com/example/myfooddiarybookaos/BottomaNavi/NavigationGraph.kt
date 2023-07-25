package com.example.myfooddiarybookaos.BottomaNavi

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfooddiarybookaos.Model.TabHome.HomeScreen
import com.example.myfooddiarybookaos.TabMyAccount.MyScreen
import com.example.myfooddiarybookaos.TabSearch.SearchScreen
import com.example.myfooddiarybookaos.TabTimeLine.TimeLineScreen
import com.example.myfooddiarybookaos.ViewModel.TodayViewModel

// NavController : 대상을 이동 시키는 요소 
@Composable
fun NavigationGraph(
    navController: NavHostController,
    todayViewModel: TodayViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute
    ){
        composable(BottomNavItem.Home.screenRoute){
            HomeScreen(todayViewModel)
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