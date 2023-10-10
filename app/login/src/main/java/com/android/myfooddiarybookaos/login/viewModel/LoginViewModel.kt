package com.android.myfooddiarybookaos.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
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
    ){
        val (state, token) = repository.loginUserRequest(email,pw)
        Log.d("state",state)
    }

    fun createUser(
        email: String, pw: String,
    ){
        val (state, token) = repository.createUserRequest(email,pw)
    }
}