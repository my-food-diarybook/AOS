package com.android.myfooddiarybookaos.data.dataLogin.repository

import android.content.Context
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.data.state.LoadState
import com.android.myfooddiarybookaos.model.login.CreateUserResponse
import com.android.myfooddiarybookaos.model.login.LoginResponse
import com.android.myfooddiarybookaos.model.login.PasswordResetRequest
import com.android.myfooddiarybookaos.model.login.UserRequest
import com.android.myfooddiarybookaos.model.response.NotStateResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val networkManager: NetworkManager,
    private val context: Context
) {

    private val manager = networkManager.getLoginApiService()


    suspend fun checkTokenState() {
        manager.userIsLogin()
    }

    fun createUserRequest(
        email: String, pw: String,
        result: (status: String, token: String?) -> Unit
    ) {
        try {
            manager.createUser(UserRequest(email, pw))
                .enqueue(object : Callback<CreateUserResponse> {
                    override fun onResponse(
                        call: Call<CreateUserResponse>,
                        response: Response<CreateUserResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()!!.passwordStatus == "SUCCESS") {
                                result("SUCCESS", response.body()?.token)
                            } else {
                                if (response.body()!!.status == "DUPLICATED_USER") {
                                    result("DUPLICATED_USER", null)
                                } else {
                                    result("실패", null)
                                }
                            }
                        } else {
                            result("네트워크 에러", null)
                        }
                    }

                    override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                        result("실패", null)
                    }

                })
        } catch (_: Exception) {
            result("네트워크 에러", null)
        }
    }

    suspend fun loginUserRequest(
        email: String, pw: String,
        result: (status: String?, response: LoginResponse?) -> Unit
    ) {
        try {
            kotlin.runCatching {
                manager.userLogin(UserRequest(email, pw))
            }
                .onSuccess {
                    result(it.body()?.status, it.body())
                }
                .onFailure { e ->
                    val data = Gson().fromJson(
                        e.message,
                        NotStateResponse::class.java
                    )
                    result(data?.status, null)
                }
        } catch (e: Exception) {
            result("네트워크 에러", null)
        }
    }

    suspend fun resetUserPassword(
        userEmail: String,
        emailState: (String?) -> Unit
    ) {
        val response = manager.resetUserPassword(PasswordResetRequest(userEmail))
        if (response.isSuccessful) {
            emailState(response.body()?.status)
        } else {
            emailState(null)
        }
    }

    fun saveUserToken(response: LoginResponse?) {
        UserInfoSharedPreferences(context).accessToken = response?.token
        UserInfoSharedPreferences(context).refreshToken = response?.refreshToken
    }

}
