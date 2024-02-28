package com.android.myfooddiarybookaos.common.naviHost

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.android.myfooddiarybookaos.data.component.ToastMessaging
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.DetailScreen
import com.android.myfooddiarybookaos.detail.galleryUi.ui.GalleryScreen
import com.android.myfooddiarybookaos.detail.state.rememberDiaryFixState
import com.android.myfooddiarybookaos.home.ui.HomeDayScreen
import com.android.myfooddiarybookaos.search.state.rememberSearchDataState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainNaviHost(
    appState: ApplicationState,
    diaryState: DiaryState,
) {
    val searchState = rememberSearchDataState()
    val diaryFixState = rememberDiaryFixState()
    val scope = rememberCoroutineScope()
    val homeUpdate = remember { mutableStateOf(false) }
    val timeLineUpdate = remember { mutableStateOf(false) }
    val searchUpdate = remember { mutableStateOf(false) }
    val myUpdate = remember { mutableStateOf(false) }
    val homeDayUpdate = remember { mutableStateOf(false) }
    val detailUpdate = remember { mutableStateOf(false) }

    if (diaryState.isViewUpdate.value || detailUpdate.value) {
        timeLineUpdate.value = true
        homeUpdate.value = true
        searchUpdate.value = true
        myUpdate.value = true
        homeDayUpdate.value = true
        detailUpdate.value = false
    }

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
            bottomGraph(
                appState,
                diaryState,
                searchState,
                homeUpdate,
                timeLineUpdate,
                searchUpdate,
                myUpdate
            )

            composable(ScreenRoot.HOME_DAY) {
                HomeDayScreen(homeDayUpdate,diaryState, appState)
            }

            composable(ScreenRoot.DETAIL_DIARY) {
                DetailScreen(
                    detailUpdate,
                    appState,
                    diaryState,
                    diaryFixState
                )
            }

            // 인자 전달
            composable(
                route = "${ScreenRoot.GALLERY}/{multiSelectType}/{prevImageCount}",
                arguments = listOf(
                    navArgument("multiSelectType") { type = NavType.BoolType },
                    navArgument("prevImageCount") { type = NavType.IntType }
                )
            ) { entry ->
                val isMultiSelectView = entry.arguments?.getBoolean("multiSelectType") ?: true
                val prevSelectCount = entry.arguments?.getInt("prevImageCount") ?: 0
                GalleryScreen(appState, diaryState, isMultiSelectView, prevSelectCount)
            }
        }
    }

    if (appState.toastState.value.isNotEmpty()) {
        ToastMessaging(
            message = appState.toastState.value,
            removeView = {
                appState.toastState.value = ""
            }
        )
    }

    if (diaryState.isViewUpdate.value) {
        LaunchedEffect(Unit) {
            scope.launch {
                delay(500L)
                diaryState.isViewUpdate.value = false
            }
        }
    }
}
