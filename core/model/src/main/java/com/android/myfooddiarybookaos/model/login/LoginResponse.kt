package com.android.myfooddiarybookaos.model.login

class LoginResponse(
    val token : String?,
    val refreshToken: String,
    val status : String,
) : java.io.Serializable