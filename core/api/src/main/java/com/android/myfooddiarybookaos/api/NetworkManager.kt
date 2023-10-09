package com.android.myfooddiarybookaos.api

import android.content.Context
import com.android.myfooddiarybookaos.api.userApi.UserRetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkManager(
    private val context : Context
) {
    companion object{
        private val instance : Retrofit? = null

        private fun getRetrofit(context : Context) : Retrofit{
            val tokenData = UserInfoSharedPreferences(context)
            val header = Interceptor{
                val original = it.request()
                if (tokenData.accessToken!=null && tokenData.accessToken!=""){
                    val request = original.newBuilder()
                        .header("Authorization","Bearer ${tokenData.accessToken}")
                        .build()
                    it.proceed(request)
                } else {
                    it.proceed(original)
                }
            }

            return instance ?: Retrofit.Builder()
                .baseUrl("$baseUrl/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient(header))
                .build()
        }

        // 클라이언트 빌드
        private fun buildOkHttpClient(header: Interceptor) : OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Accept", "application/json")
                    }.build())
                }.also { client ->
                    client.addInterceptor(header)
                    //로그 기록 인터셉터 등록
                    val logInterceptor = HttpLoggingInterceptor()
                    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    client.addInterceptor(logInterceptor)
                }.build()
        }
    }

    fun getLoginApiService() : UserRetrofitService =
        getRetrofit(context).create(UserRetrofitService::class.java)
}