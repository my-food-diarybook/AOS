package com.android.myfooddiarybookaos.data.dataCalendar.repository

import com.android.myfooddiarybookaos.model.DayDate
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class CustomCalendarRepository @Inject constructor(

) {
    fun getData(newDate: Date): List<DayDate> {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = newDate

        val dataSet = ArrayList<DayDate>()
        makeMonthDate(calendar, dataSet)
        return dataSet
    }

    private fun makeMonthDate(
        calendar: Calendar,
        dataSet: ArrayList<DayDate>
    ) {
        calendar.set(Calendar.DATE, 1)

        // 이전 달 세팅
        val prevTail = calendar.get(Calendar.DAY_OF_WEEK) - 1
        makePrevTail(prevTail, dataSet)

        // 현재 달 세팅
        makeCurrentMonth(calendar, dataSet)
    }

    // 블러 처리를 위한 이전 달 마지막 주 세팅
    private fun makePrevTail(
        prevTail: Int,
        dataSet: ArrayList<DayDate>
    ) {
        for (i in 1..prevTail) {
            dataSet.add(
                DayDate(0, -1)
            )
        }
    }

    private fun makeCurrentMonth(
        calender: Calendar,
        dataSet: ArrayList<DayDate>
    ) {
        val today = Calendar.getInstance() //오늘 캘린더
        val calenderDate = today.get(Calendar.DATE)
        val todayDate = today.get(Calendar.YEAR) * 12 + today.get(Calendar.MONTH) // 오늘 치환
        val currentDate = calender.get(Calendar.YEAR) * 12 + calender.get(Calendar.MONTH) //캘린더 치환
        var selected = -1
        for (i in 1..calender.getActualMaximum(Calendar.DATE)) {
            // 오늘 데이터 == 캘린더 데이터 && 현재 날짜 == i
            if (todayDate == currentDate && i == calenderDate) ++selected

            dataSet.add(
                DayDate(i, selected)
            )

            // 이후 부터는 딤처리 (+1)
            if (selected == 0) {
                selected++
            }
        }
    }

}
