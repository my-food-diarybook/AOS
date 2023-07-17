package com.example.myfooddiarybookaos.TabHome

import android.util.Log
import com.example.myfooddiarybookaos.Model.DayDate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CustomCalendar(
    private val date : Date, //현재 뷰 날짜
) {
    private val calendar : Calendar = Calendar.getInstance()
    private var calenderDate : Int = 0
    var dateList = ArrayList<DayDate>()

    var prevTail =  0 // 이전 달 끝부분
    init {
        calendar.time = date
        calenderDate = calendar.get(Calendar.DATE)
    }

    companion object {
        const val DAY_OF_WEEK = 7
        const val LOW_OF_CALENDAR = 5
    }

    fun initBaseCalendar(){
        makeMonthDate()
    }
    private fun makeMonthDate(){
        dateList.clear()
        // 현재 일 세팅
        calendar.set(Calendar.DATE,1)

        // 이전 달 세팅
        prevTail = calendar.get(Calendar.DAY_OF_WEEK)-1
        makePrevTail()

        makeCurrentMonth(calendar)
    }


    // 블러 처리를 위한 이전 달 마지막 주 세팅
    private fun makePrevTail(){
        for (i in 1..prevTail){
            dateList.add(
                DayDate(0,-1)
            )
        }
    }

    private fun makeCurrentMonth(calender: Calendar){
        val today = Calendar.getInstance() //오늘 캘린더
        val todayDate = today.get(Calendar.YEAR)*12+today.get(Calendar.MONTH) // 오늘 치환
        val currentDate = calender.get(Calendar.YEAR)*12+calendar.get(Calendar.MONTH) //캘린더 치환
        var selected = -1
        for (i in 1..calender.getActualMaximum(Calendar.DATE)) {
            // 오늘 데이터 == 캘린더 데이터 && 현재 날짜 == i
            if (todayDate==currentDate && i == calenderDate) ++selected

            dateList.add(
                DayDate(i,selected)
            )

            // 이후 부터는 딤처리 (+1)
            if (selected==0){
                selected++
            }
        }
    }
}