package com.android.myfooddiarybookaos.common.BottomaNavi

// sealed class : 유연한 enum class
// 이 클래스를 사용하려면 무조건 같은 파일 내에 있어야 함
// 상태값이 변하지 않는 서브 클래스 객체를 사용하므로 object 사용
// 기존의 Menu 기능
sealed class BottomNavItem(
    val title : String, val icon : Int?, val screenRoute : String
){
    object Home : BottomNavItem(MainScreen.Home.name, MainIcon.homeIcon, MainScreen.Home.name)
    object TimeLine : BottomNavItem(
        MainScreen.TimeLine.name,
        MainIcon.timeLineIcon, MainScreen.TimeLine.name)
    object Spacer : BottomNavItem(MainScreen.Spacer.name,null, MainScreen.Spacer.name)
    object Search : BottomNavItem(MainScreen.Search.name, MainIcon.searchIcon, MainScreen.Search.name)
    object MyAccount : BottomNavItem(
        MainScreen.MyAccount.name,
        MainIcon.myAccountIcon, MainScreen.MyAccount.name)
}

