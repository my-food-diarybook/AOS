package com.android.myfooddiarybookaos.common.naviHost

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.common.bottomaNavi.BottomNavigation
import com.android.myfooddiarybookaos.common.bottomaNavi.bottomGraph
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
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
            FloatingActionButton(
                modifier = Modifier
                    .size(64.dp)
                    .padding(5.33.dp),
                backgroundColor = Color.White,
                contentColor = colorResource(id = R.color.main_color),
                onClick = {
                    diaryState.showSelectView.value = true
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.add_button),
                    null,
                )
            }
        }
    ) {
        NavHost(
            appState.navController,
            startDestination = ScreenRoot.MAIN_GRAPH
        ){
            // main View
            bottomGraph(appState,diaryState)

            composable(ScreenRoot.HOME_DAY){
                HomeDayScreen(diaryState,appState)
            }
        }
    }
}