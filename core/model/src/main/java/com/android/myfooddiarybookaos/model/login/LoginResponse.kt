package com.android.myfooddiarybookaos.model.login

class LoginResponse(
    val token : String?,
    val refreshToken: String,
    val status : String,
    val pwExpired: Boolean
) : java.io.Serializable