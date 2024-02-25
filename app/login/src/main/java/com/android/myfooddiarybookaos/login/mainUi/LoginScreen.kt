package com.android.myfooddiarybookaos.login.mainUi

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.android.myfooddiarybookaos.login.mainSubUi.BottomLayout
import com.android.myfooddiarybookaos.login.mainSubUi.MidLayout
import com.android.myfooddiarybookaos.login.mainSubUi.TopLayout
import com.android.myfooddiarybookaos.login.navi.LoginScreenRoot

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LoginScreen(navController: NavHostController) {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {})

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopLayout()
        MidLayout(
            findPassword = { navController.navigate(LoginScreenRoot.FIND_PASSWORD) },
            changePassword = { navController.navigate(LoginScreenRoot.NEW_PASSWORD) }
        )
        BottomLayout(
            findPassword = { navController.navigate(LoginScreenRoot.FIND_PASSWORD) },
            insertUser = { navController.navigate(LoginScreenRoot.INSERT) }
        )
    }
}
