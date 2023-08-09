package com.android.myfooddiarybookaos.home

import java.util.*

interface CustomCalendarInterFace {
    fun initBaseCalendar()
    fun initData(newDate : Date)
    fun makeMonthDate()
    fun makePrevTail()
    fun makeCurrentMonth(calender: Calendar)
}