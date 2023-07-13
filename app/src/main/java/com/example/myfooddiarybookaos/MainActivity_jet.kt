package com.example.myfooddiarybookaos
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.myfooddiarybookaos.Data.MainScreen
import com.example.myfooddiarybookaos.TabHome.HomeScreen
import com.example.myfooddiarybookaos.ui.theme.MyFoodDiaryBookAOSTheme
import org.mozilla.javascript.tools.jsc.Main


class MainActivity_jet : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFoodDiaryBookAOSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainUi()
                }
            }
        }
    }
}

@Composable
fun MainUi() {

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        // constraintLayout id
        val (button,box) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(box) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        ){
            //bottom navi bar를 위한 navHost 지정
            // -> 구성 가능한 대상은 컴포저블 간에 이동할 수 있어야 함 (fragment navi화)
            val navigator = rememberNavController()
            NavHost(
                    navController = navigator,
                    startDestination = MainScreen.Home.name
            ){
                composable(MainScreen.Home.name){
                    HomeScreen()
                }
                composable(MainScreen.TimeLine.name){}
                composable(MainScreen.Search.name){}
                composable(MainScreen.MyAccount.name){}
            }


        }
        // button floating button
        Image(
            painter = painterResource(id = R.drawable.baseline_add_circle_24)
            , contentDescription ="",
            Modifier
                .width(dimensionResource(id = R.dimen.size_53_33))
                .height(dimensionResource(id = R.dimen.size_53_33))
                .paint(painterResource(id = R.drawable.circle))
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyFoodDiaryBookAOSTheme {
        MainUi()

    }
}