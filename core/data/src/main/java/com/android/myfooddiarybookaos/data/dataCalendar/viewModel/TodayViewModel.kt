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
    val todayRepository: TodayRepository,
    private val calendarRepository: CustomCalendarRepository
) : ViewModel() {

    fun setCurrentDate(year: Int, month: Int) {
        todayRepository.setCurrentDate(year, month)
    }

    fun getCustomCalendar() : List<DayDate>{
        return todayRepository.currentCalendar.value?.time.let {
            if (it==null) listOf()
            else calendarRepository.getData(it)
        }
    }

    fun getTodayDate(): String {
        val now: LocalDateTime = LocalDateTime.now()
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    @SuppressLint("SimpleDateFormat")
    fun getDayDate(day: Int): String? {
        val dateFormat = "yyyy-MM-dd"
        val date = todayRepository.currentCalendar.value
        date?.set(Calendar.DAY_OF_MONTH, day)
        return date?.time?.let { SimpleDateFormat(dateFormat).format(it) }
    }

    fun getTopDate(date: String?): String {
        if (date == null || date == "") return ""
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val topDate = LocalDate.parse(date, formatter)
        return "${topDate.monthValue}/${topDate.dayOfMonth} ${getLocalDateDayOfWeek(topDate)}"
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentYearMonth(): String? {
        val dateFormat = "yyyy-MM"
        val date = todayRepository.currentCalendar.value?.time
        return if (date == null) null
        else SimpleDateFormat(dateFormat).format(date)
    }
}