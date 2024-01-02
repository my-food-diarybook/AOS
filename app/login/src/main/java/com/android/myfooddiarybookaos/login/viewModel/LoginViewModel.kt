package com.android.myfooddiarybookaos.login.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.data.dataLogin.repository.LoginRepository
import com.android.myfooddiarybookaos.login.data.GoogleLoginRepository
import com.android.myfooddiarybookaos.model.login.LoginResponse
import com.android.myfooddiarybookaos.model.login.SsoToken
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val googleLoginRepository: GoogleLoginRepository
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
            result = { _, _ ->
                loginUser(email, pw, userState = { userState(it) })
            }
        )
    }

    private fun saveUserState(
        status: String,
        response: LoginResponse?
    ): Boolean {
        return if (status == "성공") {
            // 토큰 저장 !
            repository.saveUserToken(response)
            true
        } else false
    }

    fun goMain(
        context: Context,
        userEmail: String
    ) {
        val intent = Intent(
            context,
            Class.forName("com.android.myfooddiarybookaos.MainActivity")
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        UserInfoSharedPreferences(context).userEmail = userEmail
        context.startActivity(intent)
    }

    fun setLauncher(
        result: ActivityResult,
        firebaseAuth: FirebaseAuth,
        loginState: (Boolean) -> Unit
    ) {
        googleLoginRepository.setLauncher(result, firebaseAuth, loginCallback = {
            if (it != null) {
                it.let { token ->
                    CoroutineScope(Dispatchers.IO).launch {
                        googleLoginRepository.loginRequest(token, result = { resultState ->
                            if (resultState) {
                                loginState(true)
                            } else loginState(false)
                        })
                    }
                }
            } else {
                loginState(false)
            }
        })
    }

    fun goggleLogin(launcher: ActivityResultLauncher<Intent>) {
        googleLoginRepository.login(NetworkManager.GOOGLE_ID, launcher)
    }


}