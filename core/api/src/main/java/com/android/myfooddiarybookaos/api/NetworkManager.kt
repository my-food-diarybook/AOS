package com.android.myfooddiarybookaos.api

import android.content.Context
import com.android.myfooddiarybookaos.api.diaryApi.DiaryPostRetrofitService
import com.android.myfooddiarybookaos.api.diaryApi.DiaryRetrofitService
import com.android.myfooddiarybookaos.api.diaryApi.TimeLineRetrofitService
import com.android.myfooddiarybookaos.api.myApi.MyRetrofitService
import com.android.myfooddiarybookaos.api.refresh.AuthInterceptor
import com.android.myfooddiarybookaos.api.refresh.TokenRetrofitService
import com.android.myfooddiarybookaos.api.searchApi.SearchRetrofitService
import com.android.myfooddiarybookaos.api.userApi.UserRetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkManager(
    private val context: Context
) {
    companion object {
        private val instance: Retrofit? = null
        private const val CONTENT_APPLICATION = "application/json"
        private const val CONTENT_MULTI_PART = "multipart/form-data"
        const val GOOGLE_ID = "859792891234-paskj6t339bdd09gu1juaigf4f7jqhn4.apps.googleusercontent.com"
        const val LOGIN_NONE ="none"
        const val LOGIN_KAKAO = "kakao"
        const val LOGIN_GOOGLE = "google"

        private fun getRetrofit(
            context: Context,
            contentType: String,
        ): Retrofit {
            val userData = UserInfoSharedPreferences(context)
            val loginForm = userData.loginForm ?: LOGIN_NONE
            val header = Interceptor {
                val original = it.request()
                if (userData.accessToken != null && userData.accessToken != "") {
                    val request = original.newBuilder()
                        .header("token", "${userData.accessToken}")
                        .build()
                    it.proceed(request)
                } else {
                    it.proceed(original)
                }
            }

            return instance ?: Retrofit.Builder()
                .baseUrl("$BASE_URL/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(unsafeOkHttpClient(header, contentType, loginForm,context))
                .build()

        }

        // 클라이언트 빌드
//        private fun buildOkHttpClient(header: Interceptor) : OkHttpClient {
//            return OkHttpClient.Builder()
//                .addInterceptor { chain ->
//                    chain.proceed(chain.request().newBuilder().also {
//                        it.addHeader("Accept", "application/json")
//                    }.build())
//                }.also { client ->
//                    client.addInterceptor(header)
//                    //로그 기록 인터셉터 등록
//                    val logInterceptor = HttpLoggingInterceptor()
//                    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
//                    client.addInterceptor(logInterceptor)
//                }.경

        // SSL 인증서 체크 + 클라이언트
        private fun unsafeOkHttpClient(
            header: Interceptor,
            contentType: String,
            loginForm: String,
            context: Context,
        ): OkHttpClient {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out java.security.cert.X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out java.security.cert.X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }

            builder.addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("login-from", loginForm)
                    it.addHeader("Content-Type", contentType)
                    it.addHeader("request-agent","android; app-version $appVersion")
                }.build())
            }.also { client ->
                client.addInterceptor(header)
                client.addInterceptor(AuthInterceptor(context,loginForm))
                //로그 기록 인터셉터 등록
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client
                    .addInterceptor(logInterceptor)
                    .connectTimeout(NETWORK_TIME_OUT_SECOND, TimeUnit.SECONDS)
                    .readTimeout(NETWORK_TIME_OUT_SECOND, TimeUnit.SECONDS)
            }
            return builder.build()

        }

        private const val NETWORK_TIME_OUT_SECOND = 10L
    }

    fun getLoginApiService(): UserRetrofitService =
        getRetrofit(context, CONTENT_APPLICATION).create(UserRetrofitService::class.java)

    fun getDiaryMultiPartApiService(): DiaryPostRetrofitService =
        getRetrofit(context, CONTENT_MULTI_PART).create(DiaryPostRetrofitService::class.java)

    fun getDiaryAppApiService(): DiaryRetrofitService =
        getRetrofit(context, CONTENT_APPLICATION).create(DiaryRetrofitService::class.java)

    fun getTimeLineApiService():TimeLineRetrofitService =
        getRetrofit(context, CONTENT_APPLICATION).create(TimeLineRetrofitService::class.java)

    fun getMyApiService(): MyRetrofitService =
        getRetrofit(context,CONTENT_APPLICATION).create(MyRetrofitService::class.java)

    fun getSearchApiService(): SearchRetrofitService =
        getRetrofit(context, CONTENT_APPLICATION).create(SearchRetrofitService::class.java)

}