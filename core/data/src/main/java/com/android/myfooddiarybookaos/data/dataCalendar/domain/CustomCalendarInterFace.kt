package com.android.myfooddiarybookaos.data.dataCalendar.domain

import java.util.*

interface CustomCalendarInterFace {
    fun initBaseCalendar()
    fun initData(newDate : Date)
    fun makeMonthDate()
    fun makePrevTail()
    fun makeCurrentMonth(calender: Calendar)
}