package com.android.myfooddiarybookaos.data.state

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Stable
class ApplicationState(
    val bottomBarState: MutableState<Boolean>,
    val navController: NavHostController,
    val addFloatButtonState: MutableState<Boolean>,
    val scaffoldState: ScaffoldState,
) {}