package com.android.myfooddiarybookaos.myaccount.navi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.myaccount.myMain.MyMainScreen
import com.android.myfooddiarybookaos.myaccount.notice.NoticeScreen

@Composable
fun MyGraph(
    myNavi: NavHostController
) {

    NavHost(
        navController = myNavi,
        startDestination = MyScreenRoot.MY
    ){

        composable(MyScreenRoot.MY){
            MyMainScreen()
        }

        composable(MyScreenRoot.NOTICE){
            NoticeScreen()
        }
    }
}