package com.android.myfooddiarybookaos.login.di

import android.content.Context
import com.android.myfooddiarybookaos.api.KakaoApiManager
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.login.data.GoogleLoginRepository
import com.android.myfooddiarybookaos.login.data.KaKaoLoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    @ViewModelScoped
    fun bindGoogle(
        @ApplicationContext context: Context,
        networkManager: NetworkManager
    ) = GoogleLoginRepository(context,networkManager)

    @Provides
    @ViewModelScoped
    fun bindKaKao(
        @ApplicationContext context: Context,
        kakaoApiManager: KakaoApiManager
    ) = KaKaoLoginRepository(context,kakaoApiManager.getKakaoLoginService())
}