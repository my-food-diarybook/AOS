package com.android.myfooddiarybookaos.home.calendar


import com.android.myfooddiarybookaos.model.DayDate
import java.util.*
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class CustomCalendar : CustomCalendarInterFace {
    val calendar : Calendar = Calendar.getInstance()
    private var calenderDate : Int = 0
    var dateSet = ArrayList<DayDate>()
    var prevTail =  0 // 이전 달 끝부분
    init {
        calenderDate = calendar.get(Calendar.DATE)
        initBaseCalendar()

    }

//    companion object {
//        const val DAY_OF_WEEK = 7
//        const val LOW_OF_CALENDAR = 5
//    }

    override fun initBaseCalendar(){
        makeMonthDate()
    }

    override fun initData(newDate : Date){
        calendar.time = newDate
        calenderDate = calendar.get(Calendar.DATE)
        makeMonthDate()

    }
    override fun makeMonthDate(){
        dateSet.clear()
        // 현재 일 세팅
        calendar.set(Calendar.DATE,1)

        // 이전 달 세팅
        prevTail = calendar.get(Calendar.DAY_OF_WEEK)-1
        makePrevTail()

        // 현재 달 세팅
        makeCurrentMonth(calendar)
    }


    // 블러 처리를 위한 이전 달 마지막 주 세팅
    override fun makePrevTail(){
        for (i in 1..prevTail){
            dateSet.add(
                DayDate(0,-1)
            )
        }
    }

    override fun makeCurrentMonth(calender: Calendar){
        val today = Calendar.getInstance() //오늘 캘린더
        val todayDate = today.get(Calendar.YEAR)*12+today.get(Calendar.MONTH) // 오늘 치환
        val currentDate = calender.get(Calendar.YEAR)*12+calendar.get(Calendar.MONTH) //캘린더 치환
        var selected = -1
        for (i in 1..calender.getActualMaximum(Calendar.DATE)) {
            // 오늘 데이터 == 캘린더 데이터 && 현재 날짜 == i
            if (todayDate==currentDate && i == calenderDate) ++selected

            dateSet.add(
                DayDate(i,selected)
            )

            // 이후 부터는 딤처리 (+1)
            if (selected==0){
                selected++
            }
        }
    }
}