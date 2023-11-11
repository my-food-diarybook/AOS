package com.android.myfooddiarybookaos.data.dataCalendar.domain

import java.util.*

interface CustomCalendarInterFace {
    fun initBaseCalendar()
    fun getData(newDate : Date)
    fun makeMonthDate()
    fun makePrevTail()
    fun makeCurrentMonth(calender: Calendar)
}