package com.android.myfooddiarybookaos.common.bottomaNavi

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

import com.android.myfooddiarybookaos.TabMyAccount.MyScreen
import com.android.myfooddiarybookaos.TabSearch.SearchScreen
import com.android.myfooddiarybookaos.TabTimeLine.TimeLineScreen
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState

import com.android.myfooddiarybookaos.home.HomeScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

// NavController : 대상을 이동 시키는 요소 

fun NavGraphBuilder.bottomGraph(
    appState: ApplicationState,
    diaryState : DiaryState
) {
    navigation(
        startDestination = BottomNavItem.Home.screenRoute,
        route = ScreenRoot.MAIN_GRAPH
    ){
        composable(BottomNavItem.Home.screenRoute){
            HomeScreen(diaryState,appState)
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