package com.android.myfooddiarybookaos.data.dataCalendar.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.repository.TodayRepository
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    val todayRepository: TodayRepository
):ViewModel() {
    fun setCurrentDate(year : Int, month : Int){
        todayRepository.setCurrentDate(year,month)
    }

    fun getCurrentDate(): String{
        val now: LocalDateTime = LocalDateTime.now()
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentYearMonth(): String? {
        val dateFormat = "yyyy-MM"
        val date = todayRepository.currentCalendar.value?.time
        return if (date == null) null
               else SimpleDateFormat(dateFormat).format(date)
    }
}