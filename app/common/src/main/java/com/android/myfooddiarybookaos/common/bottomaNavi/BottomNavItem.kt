package com.android.myfooddiarybookaos.common.bottomaNavi

import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: Int?,
    val unselectedIcon: Int?,
    val screenRoute: String
) {
    object Home : BottomNavItem(
        ScreenRoot.HOME,
        MainSelectedIcon.homeIcon,
        MainUnSelectedIcon.homeIcon,
        ScreenRoot.HOME
    )

    object TimeLine : BottomNavItem(
        ScreenRoot.TIMELINE,
        MainSelectedIcon.timeLineIcon,
        MainUnSelectedIcon.timeLineIcon,
        ScreenRoot.TIMELINE
    )

    object Spacer : BottomNavItem(ScreenRoot.SPACER, null, null, ScreenRoot.SPACER)

    object Search : BottomNavItem(
        ScreenRoot.SEARCH,
        MainSelectedIcon.searchIcon,
        MainUnSelectedIcon.searchIcon,
        ScreenRoot.SEARCH
    )

    object MyAccount : BottomNavItem(
        ScreenRoot.MY_ACCOUNT,
        MainSelectedIcon.myAccountIcon,
        MainUnSelectedIcon.myAccountIcon,
        ScreenRoot.MY_ACCOUNT
    )
}
