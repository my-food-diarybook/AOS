package com.android.myfooddiarybookaos.login.data

import android.content.Context
import android.util.Log
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.kakao.KakaoRetrofitService
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.AccessTokenInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class KaKaoLoginRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val kakaoService : KakaoRetrofitService
) {

    fun kaKaoLogin(callback : (OAuthToken?, Throwable?)  -> Unit,loginCallback: (String?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)){ //앱 설치 상태
            UserApiClient.instance.loginWithKakaoTalk(context){ _, error ->
                if (error != null){

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인 취소의 경우
                    // 의도적 로그인 취소로 판단. 카카오 계정으로 로그인 시도 없이 로그인 취소 처리 (아무런 동작 x)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        loginCallback(null)
                        return@loginWithKakaoTalk
                    }

                    // 앱설치 -> 접근 오류 -> 카카오 계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback =  callback)

                }
            }
        } else { //앱 비설치 상태 -> 카카오 계정으로 로그인 시도
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }


    fun checkLogin(): Boolean {
        // 이미 로그인 상태
        var check = false
        if (AuthApiClient.instance.hasToken()){
            UserApiClient.instance.accessTokenInfo { _, error->
                check = if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        false
                    } else {
                        false
                    }
                } else {
                    // 토큰 유효성 체크 성공  ( 필요 시 토큰 재 갱신)
                    true
                }
            }
        } else {
            // 토큰이 없음
            check = false
        }
        return check
    }

    fun getUserInfo(token : String?, loginCallback: (String?) -> Unit) {
        val email = kakaoService.getUserInfo(token).kakao_account.email
        loginCallback(email)
//        UserApiClient.instance.me { user, error ->
//            Log.d("user",user?.kakaoAccount?.email.toString())
//            Log.d("user",error.toString())
//            if (error != null){
//                loginCallback(null)
//            }
//            else if (user != null){
//                loginCallback(user.kakaoAccount?.email) // 로그인 정보와 만료 기간 전송
//            }
//            else {
//                loginCallback(null)
//            }
//        }
    }
}