package com.android.myfooddiarybookaos.data.dataCalendar.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import com.android.myfooddiarybookaos.home.function.getLocalDateDayOfWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    val todayRepository: TodayRepository
):ViewModel() {
    private var homeDay: String? = null
    fun setCurrentDate(year : Int, month : Int){
        todayRepository.setCurrentDate(year,month)
    }

    fun getCurrentDate(): String{
        val now: LocalDateTime = LocalDateTime.now()
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    @SuppressLint("SimpleDateFormat")
    fun setDayDate(day: Int) {
        val dateFormat = "yyyy-MM-dd"
        val date = todayRepository.currentCalendar.value
        date?.set(Calendar.DAY_OF_MONTH, day)
        date?.time?.let { homeDay = SimpleDateFormat(dateFormat).format(it) }
    }

    fun getTopDate(date: String?): String{
        if (date == null) return ""
        val now = LocalDate.now()
        val strNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val dateNow = LocalDate.parse(strNow,DateTimeFormatter.ISO_DATE)
        return "${dateNow.monthValue}/${dateNow.dayOfMonth} ${getLocalDateDayOfWeek(dateNow)}"
    }

    fun getDayDate(): String? = homeDay
    fun reSetDayDate(){ homeDay = null }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentYearMonth(): String? {
        val dateFormat = "yyyy-MM"
        val date = todayRepository.currentCalendar.value?.time
        return if (date == null) null
               else SimpleDateFormat(dateFormat).format(date)
    }
}