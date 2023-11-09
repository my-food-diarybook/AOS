package com.dnd_9th_3_android.gooding.common.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavBackStackEntry
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

// bottom bar 뷰 보이는 관리
@Composable
fun ManageBottomBarState(
    navBackStackEntry: NavBackStackEntry?,
    bottomBarState : MutableState<Boolean>
) {
    when (navBackStackEntry?.destination?.route){
        ScreenRoot.HOME, ScreenRoot.SPACER,ScreenRoot.TIMELINE,ScreenRoot.MY_ACCOUNT,
            ScreenRoot.SEARCH
        ->{
            bottomBarState.value = true
        }
        ScreenRoot.HOME_DAY
        -> {
            bottomBarState.value = false
        }
    }
}