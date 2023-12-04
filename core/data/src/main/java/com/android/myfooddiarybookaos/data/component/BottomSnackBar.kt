package com.android.myfooddiarybookaos.data.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSnackBar(
    lifecycleScope : CoroutineScope,
    textState : String
) {
    val scaffoldState = rememberScaffoldState()
    lifecycleScope.launch {
        scaffoldState.snackbarHostState.showSnackbar(textState)
    }
}