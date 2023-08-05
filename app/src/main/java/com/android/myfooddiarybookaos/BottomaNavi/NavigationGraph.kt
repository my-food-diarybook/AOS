package com.android.myfooddiarybookaos.BottomaNavi

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.TabHome.HomeScreen
import com.android.myfooddiarybookaos.TabMyAccount.MyScreen
import com.android.myfooddiarybookaos.TabSearch.SearchScreen
import com.android.myfooddiarybookaos.TabTimeLine.TimeLineScreen
import com.android.myfooddiarybookaos.ViewModel.TodayViewModelInterface

// NavController : 대상을 이동 시키는 요소 
@Composable
fun NavigationGraph(
    navController: NavHostController,
    todayViewModel: TodayViewModelInterface
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute
    ){
        composable(BottomNavItem.Home.screenRoute){
            HomeScreen(todayViewModel)
        }
        composable(BottomNavItem.TimeLine.screenRoute){
            TimeLineScreen(todayViewModel)
        }
        composable(BottomNavItem.Search.screenRoute){
            SearchScreen()
        }
        composable(BottomNavItem.MyAccount.screenRoute){
            MyScreen()
        }
    }

}