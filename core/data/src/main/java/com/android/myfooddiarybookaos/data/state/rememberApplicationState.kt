package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberApplicationState(
    bottomBarState: MutableState<Boolean> = mutableStateOf(false),
    navController: NavHostController = rememberNavController(),
    addFloatButtonState: MutableState<Boolean> = mutableStateOf(false)
) = remember(bottomBarState,navController,addFloatButtonState){
    ApplicationState(
        bottomBarState,
        navController,
        addFloatButtonState
    )
}