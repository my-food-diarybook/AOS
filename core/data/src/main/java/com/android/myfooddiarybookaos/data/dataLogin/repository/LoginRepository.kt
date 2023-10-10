package com.android.myfooddiarybookaos.data.dataLogin.repository

import android.util.Log
import com.android.myfooddiarybookaos.api.NetworkManager
import java.lang.Exception
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val networkManager: NetworkManager
) {

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
        } catch (_: Exception){
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
        } catch (e: Exception){
            Log.d("e",e.toString())
            Pair("네트워크 에러",null)
        }
    }
}