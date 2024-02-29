package com.android.myfooddiarybookaos.common.bottomaNavi

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.android.myfooddiarybookaos.TabMyAccount.MyScreen
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.home.ui.HomeScreen
import com.android.myfooddiarybookaos.search.SearchScreen
import com.android.myfooddiarybookaos.search.state.SearchDataState
import com.android.myfooddiarybookaos.timeline.TimeLineScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

fun NavGraphBuilder.bottomGraph(
    appState: ApplicationState,
    diaryState: DiaryState,
    searchDataState: SearchDataState,
    homeUpdate: MutableState<Boolean>,
    timeLineUpdate: MutableState<Boolean>,
    searchUpdate: MutableState<Boolean>,
    myUpdate: MutableState<Boolean>
) {

    navigation(
        startDestination = BottomNavItem.Home.screenRoute,
        route = ScreenRoot.MAIN_GRAPH
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(homeUpdate, diaryState, appState)
        }
        composable(BottomNavItem.TimeLine.screenRoute) {
            TimeLineScreen(timeLineUpdate, appState, diaryState)
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen(searchUpdate, appState, diaryState, searchDataState)
        }
        composable(BottomNavItem.MyAccount.screenRoute) {
            MyScreen(myUpdate)
        }

    }

}
