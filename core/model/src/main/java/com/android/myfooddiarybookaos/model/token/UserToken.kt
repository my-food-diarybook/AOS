package com.android.myfooddiarybookaos.model.token

data class UserToken(
    val token: String,
    val tokenExpireAt: Int,
    val refreshToken: String,
    val refreshTokenExpireAt: Int,
    val status: String
) : java.io.Serializable
