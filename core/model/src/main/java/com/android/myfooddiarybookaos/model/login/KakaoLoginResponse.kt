package com.android.myfooddiarybookaos.model.login

import com.google.gson.annotations.SerializedName

data class KakaoLoginResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("connected_at") val connected_at: String,
    @SerializedName("kakao_account") val kakao_account : KaKaoAccount
)

data class KaKaoAccount(
    @SerializedName("email") val email: String
)