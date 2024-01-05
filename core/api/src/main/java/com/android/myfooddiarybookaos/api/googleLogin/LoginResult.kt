package com.android.myfooddiarybookaos.api.googleLogin

sealed class LoginResult<out T> {
    object Loading: LoginResult<Nothing>()
    object UnLoading: LoginResult<Nothing>()
    data class Success<T>(val data: T ): LoginResult<T>()
    data class Unauthorized(val throwable: Throwable): LoginResult<Nothing>()
    data class Error(val throwable: Throwable): LoginResult<Nothing>()
}