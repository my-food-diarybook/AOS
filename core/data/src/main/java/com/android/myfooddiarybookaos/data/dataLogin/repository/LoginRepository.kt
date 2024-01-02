package com.android.myfooddiarybookaos.data.dataLogin.repository

import android.content.Context
import android.util.Log
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import com.android.myfooddiarybookaos.model.login.UserRequest
import com.android.myfooddiarybookaos.model.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val networkManager: NetworkManager,
    private val context: Context
) {
    private val manager = networkManager.getLoginApiService()
    fun createUserRequest(
        email : String, pw : String,
        result : (status : String,token : String?)->Unit
    ) {
        try {
            manager.createUser(UserRequest(email,pw))
               .enqueue(object : Callback<CreateUserResponse> {
                   override fun onResponse(
                       call: Call<CreateUserResponse>,
                       response: Response<CreateUserResponse>
                   ) {
                       if(response.isSuccessful){
                           when(response.body()!!.status){
                               "SUCCESS" -> result("성공",response.body()?.token)
                               null -> result("이미 사용중인 사용자입니다.",null)
                               else-> result("실패", null)
                           }
                       }else{
                           result("네트워크 에러", null)
                       }
                   }

                   override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                       result("실패", null)
                   }

               })
        } catch (_: Exception){
            result("네트워크 에러",null)
        }
    }

    fun loginUserRequest(
        email: String, pw : String,
        result : (status : String,response: LoginResponse?)->Unit
    )  {
        try{
            manager.userLogin(UserRequest( email,pw))
                .enqueue(object : Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful){
                            when(response.body()!!.status){
                                "SUCCESS" -> result("성공",response.body())
                                else -> result("실패",null)
                            }
                        }else{
                            result("네트워크 에러",null)
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        result("네트워크 에러",null)
                    }
                })
        } catch (e: Exception){
            result("네트워크 에러",null)
        }
    }

    fun saveUserToken(response : LoginResponse?){
        UserInfoSharedPreferences(context).accessToken = response?.token
        UserInfoSharedPreferences(context).refreshToken = response?.refreshToken
        UserInfoSharedPreferences(context).loginForm = NetworkManager.LOGIN_NONE
    }
}