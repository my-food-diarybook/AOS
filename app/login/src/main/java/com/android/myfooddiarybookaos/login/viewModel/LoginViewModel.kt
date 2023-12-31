package com.android.myfooddiarybookaos.login.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.data.dataLogin.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) :ViewModel(){

    fun loginUser(
        email : String,
        pw : String,
        userState : (Boolean) -> Unit
    ){
        repository.loginUserRequest(
            email,pw,
            result = { status, token ->
                userState(saveUserState(status,token))
            }
        )

    }

    fun createUser(
        email: String, pw: String,
        userState : (Boolean) -> Unit
    ){
        repository.createUserRequest(
            email,pw,
            result = { status, token ->
                userState(saveUserState(status,token))
            }
        )
    }

    private fun saveUserState(
        status : String,
        token : String?
    ) : Boolean {
        return if (status=="성공") {
            // 토큰 저장 !
            repository.saveUserToken(token)
            true
        }
        else false
    }

    fun goMain(
        context: Context,
        userEmail: String
    ){
        val intent = Intent(
            context,
            Class.forName("com.android.myfooddiarybookaos.MainActivity")
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        UserInfoSharedPreferences(context).userEmail = userEmail
        context.startActivity(intent)
    }
}