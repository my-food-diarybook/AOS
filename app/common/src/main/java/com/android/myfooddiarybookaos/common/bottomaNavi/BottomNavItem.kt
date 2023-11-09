package com.android.myfooddiarybookaos.common.bottomaNavi

import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

// sealed class : 유연한 enum class
// 이 클래스를 사용하려면 무조건 같은 파일 내에 있어야 함
// 상태값이 변하지 않는 서브 클래스 객체를 사용하므로 object 사용
// 기존의 Menu 기능
sealed class BottomNavItem(
    val title : String, val icon : Int?, val screenRoute : String
){
    object Home : BottomNavItem(ScreenRoot.HOME, MainIcon.homeIcon, ScreenRoot.HOME)
    object TimeLine : BottomNavItem(ScreenRoot.TIMELINE, MainIcon.timeLineIcon, ScreenRoot.TIMELINE)
    object Spacer : BottomNavItem(ScreenRoot.SPACER,null, ScreenRoot.SPACER)
    object Search : BottomNavItem(ScreenRoot.SEARCH, MainIcon.searchIcon, ScreenRoot.SEARCH)
    object MyAccount : BottomNavItem(ScreenRoot.MY_ACCOUNT, MainIcon.myAccountIcon, ScreenRoot.MY_ACCOUNT)
}

