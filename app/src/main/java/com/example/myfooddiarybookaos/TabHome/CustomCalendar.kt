package com.example.myfooddiarybookaos.TabHome

import android.util.Log
import com.example.myfooddiarybookaos.Model.DayDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CustomCalendar(
    private val date : Date,
) {
    private val calendar : Calendar = Calendar.getInstance()
    var dateList = ArrayList<DayDate>()

    init {
        calendar.time = date
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

        val weak = calendar.get(Calendar.DAY_OF_WEEK)
        // 몇요일 부터 시작할 지 카운트
        for (i in 1 until weak){
            dateList.add(
                DayDate(0,-1)
            )
        }

        // 현재 시간과 같은 캘린더를 생성해서
        // 일자가 1인 날부터 비교
        val firstDay = Calendar.getInstance()
        firstDay.time = date
        firstDay.set(Calendar.DATE,1)
        for (i in 1 .. calendar.getActualMaximum(Calendar.DATE)){
            val cmp = ((firstDay.time.time-date.time) / (60 * 60 * 24 * 1000)).toInt()
            when{
                cmp <  0 -> dateList.add(DayDate(i,-1))
                cmp >  0 -> dateList.add(DayDate(i,1))
                else -> dateList.add(DayDate(i,0))

            }
            firstDay.add(Calendar.DATE,1)
        }
    }
}