package com.android.myfooddiarybookaos.login.data

import android.content.ContentValues
import androidx.activity.result.ActivityResult
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.test.isDialog
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.api.googleLogin.LoginResult
import com.android.myfooddiarybookaos.model.login.LoginGoogleResponse
import com.android.myfooddiarybookaos.model.login.SsoToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class GoogleLoginRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkManager: NetworkManager
) {

    fun setLauncher(
        result: ActivityResult,
        firebaseAuth: FirebaseAuth,
        loginCallback: (String?) -> Unit
    ) {
        var tokenId: String?
        var email: String
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                task.getResult(ApiException::class.java)?.let { account ->
                    tokenId = account.idToken
                    if (tokenId != null && tokenId != "") {
                        val credential: AuthCredential =
                            GoogleAuthProvider.getCredential(account.idToken, null)

                        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
                            if (firebaseAuth.currentUser != null) {
                                val user: FirebaseUser = firebaseAuth.currentUser!!
                                email = user.email.toString()
                                Log.e(ContentValues.TAG, "email : $email")
                                val googleSignInToken = account.idToken ?: ""
                                if (googleSignInToken != "") {
                                    loginCallback(googleSignInToken)
                                } else {
                                    loginCallback(null)
                                }
                            } else {
                                loginCallback(null)
                            }
                        }
                    }
                } ?: throw  Exception()
            } catch (e: Exception) {
                e.printStackTrace()
                loginCallback(null)
            }
        } else {
            loginCallback(null)
        }
    }

    fun login(
        clientId: String,
        launcher: ActivityResultLauncher<Intent>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientId)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            val signInIntent: Intent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }
    }

    suspend fun loginRequest(idToken: String): LoginResult<LoginGoogleResponse> {
        networkManager
            .getGoogleLoginApiService()
            .fetchGoogleAuthInfo(networkManager.googleTokenRequest(idToken))
            ?.run {
                return LoginResult.Success(this.body() ?: LoginGoogleResponse())
            } ?: return LoginResult.Error(Exception("Exception"))
    }
}