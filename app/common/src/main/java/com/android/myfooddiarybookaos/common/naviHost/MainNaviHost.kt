package com.android.myfooddiarybookaos.common.naviHost

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.myfooddiarybookaos.common.bottomaNavi.BottomNavigation
import com.android.myfooddiarybookaos.common.bottomaNavi.bottomGraph
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.DetailScreen
import com.android.myfooddiarybookaos.detail.galleryUi.ui.GalleryScreen
import com.android.myfooddiarybookaos.detail.state.rememberDiaryFixState
import com.android.myfooddiarybookaos.home.ui.HomeDayScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun MainNaviHost(
    appState: ApplicationState,
    diaryState: DiaryState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = appState.scaffoldState,
        bottomBar = { BottomNavigation(appState) },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (appState.addFloatButtonViewState.value) {
                FloatingActionButton(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(5.33.dp),
                    backgroundColor = Color.White,
                    contentColor = colorResource(id = R.color.main_color),
                    onClick = {
                        diaryState.showSelectView.value = true
                        if (diaryState.currentHomeDay.value == "") {
                            diaryState.addScreenState.value = AddScreenState.ADD_HOME_TODAY
                        } else {
                            diaryState.addScreenState.value = AddScreenState.ADD_HOME_DAY
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_button),
                        null,
                    )
                }
            }
        }
    ) {
        NavHost(
            appState.navController,
            startDestination = ScreenRoot.MAIN_GRAPH
        ) {
            // main View
            bottomGraph(appState, diaryState)

            composable(ScreenRoot.HOME_DAY) {
                HomeDayScreen(diaryState, appState)
            }

            composable(ScreenRoot.DETAIL_DIARY) {
                DetailScreen(appState, diaryState, rememberDiaryFixState())
            }

            // 인자 전달
            composable(
                route = "${ScreenRoot.GALLERY}/{multiSelectType}",
                arguments = listOf(
                    navArgument("multiSelectType") { type = NavType.BoolType }
                )
            ) { entry ->

                val isMultiSelectView = entry.arguments?.getBoolean("multiSelectType") ?: true
                GalleryScreen(appState, diaryState, isMultiSelectView)
            }
        }
    }
}