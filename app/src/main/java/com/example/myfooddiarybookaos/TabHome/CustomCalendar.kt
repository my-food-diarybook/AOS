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
    var dateList = ArrayList<DayDate>()

    var prevTail =  0 // 이전 달 끝부분
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
        // 현재 일 세팅
        calendar.set(Calendar.DATE,1)
        // 해당 달의 마지막 일 구하기
        val currentMaxDate= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // 이전 달 세팅
        prevTail = calendar.get(Calendar.DAY_OF_WEEK)-1
        makePrevTail(calendar.clone() as Calendar)

        makeCurrentMonth(calendar)
    }
//    private fun makeMonthDate(){
//        dateList.clear()
//
////        // 현재 뷰와 같은 캘린더를 생성해서
////        // 일자가 1인 날부터 비교
////        val firstDay = Calendar.getInstance()
////        firstDay.set(Calendar.DATE,1)
////        // 시간 맞추기
////        firstDay.set(Calendar.HOUR,calendar.get(Calendar.HOUR))
//
//        // 요일 정보로 이전 달 마지막 주 데이터 구성 구성
//        val weak = getWeakData(calendar.clone() as Calendar)
//
//        // 이전달 블러처리
//        for (i in 1 until weak){
//            dateList.add(
//                DayDate(0,-1)
//            )
//        }
//
//        var newCalendar =Calendar.getInstance()
//        var sf = SimpleDateFormat("yyyy-MM-dd 00:00:00") //단순 날짜 비교
//        var date = sf.parse(newCalendar.time.time.toString())
//
//        for (i in 1 .. calendar.getActualMaximum(Calendar.DATE)){
//            // 지난 달인 경우
//            val cmp = ((newCalendar.time.time - date.time) / (60 * 60 * 24 * 1000)).toInt()
//
//            when{
//                cmp <  0 -> dateList.add(DayDate(i,-1))
//                cmp >  0 -> dateList.add(DayDate(i,1))
//                else -> dateList.add(DayDate(i,0))
//
//            }
////            firstDay.add(Calendar.DATE,1)
//        }
//    }
//
//    private fun getWeakData(weakCalendar: Calendar) : Int{
//        weakCalendar.set(calendar.get(Calendar.YEAR)
//            ,calendar.get(Calendar.MONTH),1)
//        return weakCalendar.get(Calendar.DAY_OF_WEEK)
//    }

    // 블러 처리를 위한 이전 달 마지막 주 세팅
    private fun makePrevTail(calender : Calendar){
        val monthIndex = -1
        calender.set(Calendar.MONTH,calender.get(Calendar.MONTH)-1)
        val maxDate = calender.getActualMaximum(Calendar.DATE)
        var maxOffsetDate = maxDate - prevTail
        for (i in 1..prevTail){
            dateList.add(
                DayDate(++maxOffsetDate,-1)
            )
        }
    }

    private fun makeCurrentMonth(calender: Calendar){
        val today = Calendar.getInstance()

        for (i in 1..calender.getActualMaximum(Calendar.DATE)) {
//            val isToday = ()
            dateList.add(
                DayDate(i,-1)
            )
        }
    }
}