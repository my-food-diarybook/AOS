package com.dnd_9th_3_android.gooding.common.state

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

// bottom bar 뷰 보이는 관리
@Composable
fun ManageBottomBarState(
    navBackStackEntry: NavBackStackEntry?,
    appState: ApplicationState
) {
    when (navBackStackEntry?.destination?.route) {
        ScreenRoot.HOME, ScreenRoot.SPACER, ScreenRoot.TIMELINE, ScreenRoot.MY_ACCOUNT,
        ScreenRoot.SEARCH
        -> {
            appState.bottomBarState.value = true
            appState.addFloatButtonViewState.value = true
        }
        ScreenRoot.HOME_DAY
        -> {
            appState.bottomBarState.value = false
            appState.addFloatButtonViewState.value = true
        }
        ScreenRoot.DETAIL_DIARY
        -> {
            appState.bottomBarState.value = false
            appState.addFloatButtonViewState.value = false
        }
    }
}