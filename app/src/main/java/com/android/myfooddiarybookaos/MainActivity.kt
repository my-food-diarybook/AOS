package com.android.myfooddiarybookaos
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.android.myfooddiarybookaos.core.data.R
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.common.addPicture.SelectAddScreen
import com.android.myfooddiarybookaos.common.bottomaNavi.BottomNavigation
import com.android.myfooddiarybookaos.common.bottomaNavi.NavigationGraph
import com.android.myfooddiarybookaos.data.state.rememberApplicationState
import com.android.myfooddiarybookaos.data.state.rememberDiaryState
import com.android.myfooddiarybookaos.data.ui.theme.MyFoodDiaryBookAOSTheme
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import dagger.hilt.android.AndroidEntryPoint


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
                closeLog = {
                    // 취소 버튼 or 선택화면으로 전환
                    diaryState.showSelectView.value = false
                }
            )
        }
    }

    Scaffold(
        // 네비게이션 연결
        bottomBar = {
            BottomNavigation(
                navController = appState.navController
            )
        },
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
        Box(Modifier.padding(it)){
            // 각 네비게이션에 맞는 뷰를 그러주는 그래프 연결
            NavigationGraph(
                appState = appState,
                diaryState = diaryState
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyFoodDiaryBookAOSTheme {
        MainUi()

    }
}