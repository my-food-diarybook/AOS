package com.android.myfooddiarybookaos.login.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import java.lang.Exception

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) :AndroidViewModel(application ){
    private val networkManager = NetworkManager(application)

    fun createUserRequest(
        email : String, pw : String
    ) : Pair<String,String?> {
        return try {
            val response = networkManager.getLoginApiService()
                .createUser(email,pw)
            when(response.passwordStatus){
                "SUCCESS" -> Pair("성공",response.token)
                null -> Pair("이미 사용중인 사용자입니다.",null)
                else-> Pair("실패", null)
            }
        } catch (_:Exception){
            Pair("네트워크 에러",null)
        }
    }

    fun loginUserRequest(
        email: String, pw : String
    ) : Pair<String,String?> {
        return try{
            val response = networkManager.getLoginApiService()
                .userLogin(email,pw)
            when(response.status){
                "SUCCESS" -> Pair("성공",response.token)
                else -> Pair("실패",null)
            }
        } catch (_: Exception){
            Pair("네트워크 에러",null)
        }
    }
}