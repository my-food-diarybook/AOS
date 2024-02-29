package com.android.myfooddiarybookaos.common.appNavi

enum class Screens {

}

interface AppNavigator {
    fun navigateTo(screen: Screens)
}
