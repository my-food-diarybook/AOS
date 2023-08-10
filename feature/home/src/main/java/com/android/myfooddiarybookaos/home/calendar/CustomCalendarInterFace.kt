package com.android.myfooddiarybookaos.home.calendar

import java.util.*

interface CustomCalendarInterFace {
    fun initBaseCalendar()
    fun initData(newDate : Date)
    fun makeMonthDate()
    fun makePrevTail()
    fun makeCurrentMonth(calender: Calendar)
}