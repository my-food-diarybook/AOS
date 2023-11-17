package com.android.myfooddiarybookaos.data.state

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
class ApplicationState(
    val bottomBarState: MutableState<Boolean>,
    val navController: NavHostController,
    val addFloatButtonViewState: MutableState<Boolean>,
    val popAddFloatButtonState: MutableState<Boolean>,
    val scaffoldState: ScaffoldState,
) {}