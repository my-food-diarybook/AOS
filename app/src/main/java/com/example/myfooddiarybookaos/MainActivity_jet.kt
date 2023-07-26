package com.example.myfooddiarybookaos
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.rememberNavController
import com.example.myfooddiarybookaos.BottomaNavi.BottomNavigation
import com.example.myfooddiarybookaos.BottomaNavi.NavigationGraph
import com.example.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import com.example.myfooddiarybookaos.ViewModel.TodayViewModel
import com.example.myfooddiarybookaos.ui.theme.MyFoodDiaryBookAOSTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity_jet : ComponentActivity() {
    private val todayViewModel:TodayViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFoodDiaryBookAOSTheme {
                MainUi(todayViewModel)
            }
        }
    }
}

@Composable
fun MainUi(todayViewModel:TodayViewModelInterface) {
    // navController
    // 네비게이션의 중심 API -> 각 화면을 구성하는 컴포저블의 백스택을 추적
    val navController = rememberNavController()
    // 기본 material design ui 구현
    // TopAppBar, BottomAppBar, FloatingActionButton, Drawer
    Scaffold(
        // 네비게이션 연결
        bottomBar = { BottomNavigation(navController = navController)},
        floatingActionButton = {}
    ) {
        Box(Modifier.padding(it)){
            // 각 네비게이션에 맞는 뷰를 그러주는 그래프 연결
            NavigationGraph(
                navController = navController,
                todayViewModel = todayViewModel
            )
        }
    }
//    ConstraintLayout(
//        Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//    ) {
//        // constraintLayout id
//        val (button,box) = createRefs()
//        Box(
//            modifier = Modifier
//                .constrainAs(box) {
//                bottom.linkTo(parent.bottom)
//                end.linkTo(parent.end)
//                start.linkTo(parent.start)
//            }
//        ){
//
//
//
//        }
//        // button floating button
//        Image(
//            painter = painterResource(id = R.drawable.baseline_add_circle_24)
//            , contentDescription ="",
//            Modifier
//                .width(dimensionResource(id = R.dimen.size_53_33))
//                .height(dimensionResource(id = R.dimen.size_53_33))
//                .paint(painterResource(id = R.drawable.circle))
//                .constrainAs(button) {
//                    bottom.linkTo(parent.bottom)
//                    end.linkTo(parent.end)
//                    start.linkTo(parent.start)
//                }
//        )
//    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyFoodDiaryBookAOSTheme {
        MainUi(todayViewModel = FakeTodayViewModel())

    }
}