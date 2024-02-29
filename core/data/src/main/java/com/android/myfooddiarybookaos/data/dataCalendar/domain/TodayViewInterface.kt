package com.android.myfooddiarybookaos.data.todayViewModel

import java.util.Calendar

interface TodayViewInterface {
    val currentCalendar: Calendar
    fun setCurrentDate(year: Int, month: Int)
}

//for preview
//class FakeTodayView : TodayViewInterface {
//    override var dataChange: Boolean = false
//    override val currentCalendar: Calendar = Calendar.getInstance()
//    override fun setCurrentDate(year: Int, month: Int) {}
//    override fun dataChangeOn() {}
//    override fun dataChangeOff() {}
//}
