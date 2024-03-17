package com.android.myfooddiarybookaos.data.state

sealed interface LoadState {
    object Loading: LoadState
    object Init: LoadState
    object Fail: LoadState
}
