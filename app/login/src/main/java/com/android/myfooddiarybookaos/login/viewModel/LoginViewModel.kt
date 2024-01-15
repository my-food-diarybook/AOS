package com.android.myfooddiarybookaos.login.viewModel

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.api.googleLogin.LoginResult
import com.android.myfooddiarybookaos.data.dataLogin.repository.LoginRepository
import com.android.myfooddiarybookaos.login.data.GoogleLoginRepository
import com.android.myfooddiarybookaos.login.data.KaKaoLoginRepository
import com.android.myfooddiarybookaos.model.login.LoginGoogleResponse
import com.android.myfooddiarybookaos.model.login.LoginResponse
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val googleLoginRepository: GoogleLoginRepository,
    private val kaKaoLoginRepository: KaKaoLoginRepository
) : ViewModel() {

    fun loginUser(
        email: String,
        pw: String,
        userState: (Boolean) -> Unit
    ) {
        repository.loginUserRequest(
            email, pw,
            result = { status, response ->
                userState(saveUserState(status, response))
            }
        )

    }

    fun createUser(
        email: String, pw: String,
        userState: (Boolean) -> Unit
    ) {
        repository.createUserRequest(
            email, pw,
            result = { state, _ ->
                if (state=="SUCCESS") {
                    loginUser(email, pw, userState = { userState(it) })
                }else {
                    userState(false)
                }
            }
        )
    }

    private fun saveUserState(
        status: String,
        response: LoginResponse?
    ): Boolean {
        return if (status == "성공") {
            // 토큰 저장 !
            repository.saveUserToken(response, NetworkManager.LOGIN_NONE)
            true
        } else false
    }

    fun goMain(
        context: Context,
    ) {
        val intent = Intent(
            context,
            Class.forName("com.android.myfooddiarybookaos.MainActivity")
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun saveEmailState(context: Context, userEmail: String) {
        UserInfoSharedPreferences(context).userEmail = userEmail
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun setLauncher(
        result: ActivityResult,
        firebaseAuth: FirebaseAuth,
        loginState: (Boolean) -> Unit,
        saveEmailState: (String) -> Unit
    ) {
        googleLoginRepository.setLauncher(result, firebaseAuth,
            loginCallback = {
                if (it != null) {
                    it.let { token ->
                        // token decode
                        CoroutineScope(Dispatchers.IO).launch {
                            googleLoginRepository.loginRequest(token).let { result ->
                                when (result) {
                                    is LoginResult.Success<LoginGoogleResponse> -> {
                                        repository.saveUserToken(
                                            LoginResponse(
                                                refreshToken = result.data.refresh_token,
                                                status = "성공",
                                                token = result.data.access_token
                                            ),
                                            NetworkManager.LOGIN_GOOGLE
                                        )
                                        loginState(true)
                                    }
                                    else -> {
                                        loginState(false)
                                    }
                                }
                            }
                        }
                    }
                } else {
                    loginState(false)
                }
            },
            saveEmail = {
                saveEmailState(it)
            }
        )
    }

    fun goggleLogin(launcher: ActivityResultLauncher<Intent>) {
        googleLoginRepository.login(launcher)
    }


    fun kaKaoLogin(
        callback: (OAuthToken?, Throwable?) -> Unit,
        loginState: (String?) -> Unit
    ) {
        kaKaoLoginRepository.kaKaoLogin(
            callback,
            loginCallback = {
                loginState(it)
            })
    }

    fun checkKaKaoLogin(): Boolean {
        return kaKaoLoginRepository.checkLogin()
    }

    fun setCallback(
        error: Throwable?,
        token: OAuthToken?,
        loginState: (Boolean) -> Unit
    ) {
        if (token == null || error != null) {
            loginState(false)
        } else {
            repository.saveUserToken(
                response = LoginResponse(
                    token = token.accessToken,
                    status = "성공",
                    refreshToken = token.refreshToken
                ),
                currentForm = NetworkManager.LOGIN_KAKAO
            )
            loginState(true)
        }
    }

    fun getKaKaoUserEmail(email: (String?) -> Unit) {
        kaKaoLoginRepository.getUserInfo(
            email = {
                email(it)
            }
        )
    }

}