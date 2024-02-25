package com.android.myfooddiarybookaos.data.dataCalendar.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.repository.CustomCalendarRepository
import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.home.function.getLocalDateDayOfWeek
import com.android.myfooddiarybookaos.model.DayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val todayRepository: TodayRepository,
    private val calendarRepository: CustomCalendarRepository
) : ViewModel() {

    fun setCurrentDate(year: Int, month: Int) {
        todayRepository.setCurrentDate(year, month)
    }

    fun setPrevDate() {
        val nextDate = getCurrentDate() - 1
        setCurrentDate(nextDate / 12, nextDate % 12 - 1)
    }

    fun setNextDate() {
        val nextDate = getCurrentDate() + 1
        setCurrentDate(nextDate / 12, nextDate % 12 - 1)
    }

    fun getCurrentDate(): Int {
        return getCurrentCalendar().get(Calendar.YEAR) * 12 +
                getCurrentCalendar().get(Calendar.MONTH) + 1
    }

    fun getTodayCalendar(): Calendar = Calendar.getInstance()
    fun getCurrentCalendar(): Calendar = todayRepository.currentCalendar

    fun getCustomCalendar(): List<DayDate> {
        return calendarRepository.getData(todayRepository.currentCalendar.time)
    }

    fun getCurrentCalendarInfo(): String {
        val currentCalendar = getCurrentCalendar()
        return "${currentCalendar.get(Calendar.YEAR)}" +
                ".${currentCalendar.get(Calendar.MONTH).plus(1)}"
    }

    fun getTodayDate(): String {
        val now: LocalDateTime = LocalDateTime.now()
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    @SuppressLint("SimpleDateFormat")
    fun getDayDate(day: Int): String? {
        val dateFormat = "yyyy-MM-dd"
        val date = todayRepository.currentCalendar
        date.set(Calendar.DAY_OF_MONTH, day)
        return SimpleDateFormat(dateFormat).format(date.time)
    }

    fun getTopDate(date: String?): String {
        if (date == null || date == "") return ""
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val topDate = LocalDate.parse(date, formatter)
        return "${topDate.monthValue}/${topDate.dayOfMonth} ${getLocalDateDayOfWeek(topDate)}"
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentYearMonth(): String {
        val dateFormat = "yyyy-MM"
        val date = todayRepository.currentCalendar.time
        return SimpleDateFormat(dateFormat).format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTimeLineKey(): String {
        val dateFormat = "yyyy-MM-01"
        val date = todayRepository.currentCalendar.time
        return SimpleDateFormat(dateFormat).format(date)
    }
}
