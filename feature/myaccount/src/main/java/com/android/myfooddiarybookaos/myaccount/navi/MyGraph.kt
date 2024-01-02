package com.android.myfooddiarybookaos.myaccount.navi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.myaccount.myInfo.MyInfoScreen
import com.android.myfooddiarybookaos.myaccount.myMain.MyMainScreen
import com.android.myfooddiarybookaos.myaccount.myNotice.NoticeScreen

@Composable
fun MyGraph(
    myNavi: NavHostController
) {

    NavHost(
        navController = myNavi,
        startDestination = MyScreenRoot.MY
    ){

        composable(MyScreenRoot.MY){
            MyMainScreen(myNavi)
        }

        composable(MyScreenRoot.NOTICE){
            NoticeScreen(myNavi)
        }

        composable(MyScreenRoot.INFO){
            MyInfoScreen(myNavi)
        }
    }
}