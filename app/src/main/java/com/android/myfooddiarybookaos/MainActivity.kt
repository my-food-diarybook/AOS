package com.android.myfooddiarybookaos
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.myfooddiarybookaos.common.addPicture.SelectAddScreen
import com.android.myfooddiarybookaos.common.naviHost.MainNaviHost
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.data.state.rememberApplicationState
import com.android.myfooddiarybookaos.data.state.rememberDiaryState
import com.android.myfooddiarybookaos.data.ui.theme.MyFoodDiaryBookAOSTheme
import com.dnd_9th_3_android.gooding.common.state.ManageBottomBarState
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import dagger.hilt.android.AndroidEntryPoint
import com.kakao.sdk.common.util.Utility

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyFoodDiaryBookAOSTheme {
                MainUi()
            }
        }
    }
}

@Composable
fun MainUi() {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    // 이미지 추가 시 다이어리 상태 변경
    val diaryState = rememberDiaryState()
    // bottom state
    val appState = rememberApplicationState()
    // mid click event
    if (diaryState.showSelectView.value){
        BottomSheetDialog(
            onDismissRequest = {
                diaryState.showSelectView.value = false
            },
            properties = BottomSheetDialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            SelectAddScreen(
                diaryState = diaryState,
                appState = appState,
                closeLog = {
                    // 취소 버튼 or 선택화면으로 전환
                    diaryState.showSelectView.value = false
                }
            )
        }
    }

    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    ManageBottomBarState(
        navBackStackEntry = navBackStackEntry,
        appState = appState
    )
    MainNaviHost(appState = appState, diaryState = diaryState)
}


//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    MyFoodDiaryBookAOSTheme {
//        MainUi()
//
//    }
//}