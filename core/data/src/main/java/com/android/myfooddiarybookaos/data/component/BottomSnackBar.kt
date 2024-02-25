package com.android.myfooddiarybookaos.data.component

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSnackBar(
    lifecycleScope: CoroutineScope,
    textState: String
) {
    val scaffoldState = rememberScaffoldState()
    lifecycleScope.launch {
        scaffoldState.snackbarHostState.showSnackbar(textState)
    }
}
