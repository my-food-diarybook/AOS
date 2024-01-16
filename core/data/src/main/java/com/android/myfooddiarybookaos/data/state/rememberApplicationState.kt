package com.android.myfooddiarybookaos.data.state

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
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
    addFloatButtonViewState: MutableState<Boolean> = mutableStateOf(false),
    popAddFloatButtonState: MutableState<Boolean> = mutableStateOf(false),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    toastState: MutableState<String> = mutableStateOf("")
) = remember(
    bottomBarState,
    navController,
    addFloatButtonViewState,
    popAddFloatButtonState,
    scaffoldState,
    toastState
) {
    ApplicationState(
        bottomBarState,
        navController,
        addFloatButtonViewState,
        popAddFloatButtonState,
        scaffoldState,
        toastState
    )
}