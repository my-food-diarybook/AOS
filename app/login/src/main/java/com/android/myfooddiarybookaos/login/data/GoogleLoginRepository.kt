package com.android.myfooddiarybookaos.login.data

import androidx.activity.result.ActivityResult
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.android.myfooddiarybookaos.api.NetworkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleLoginRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkManager: NetworkManager
) {

    fun setLauncher(
        result: ActivityResult,
        firebaseAuth: FirebaseAuth,
        loginCallback: (String?) -> Unit
    ){
        var tokenId: String?
        var email: String

        if (result.resultCode == AppCompatActivity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                task.getResult(ApiException::class.java)?.let { account ->
                    tokenId = account.idToken
                    if (tokenId != null && tokenId != ""){
                        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                        val user: FirebaseUser = firebaseAuth.currentUser!!
                        email = user.email.toString()
                    }
                }
            } catch (e: Exception){
                e.printStackTrace()
                loginCallback(null)
            }
        } else { loginCallback(null)}
    }
}