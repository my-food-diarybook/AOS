package com.android.myfooddiarybookaos.data.dataCalendar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewInterface
import java.util.*
import javax.inject.Inject

class TodayRepository @Inject constructor() : TodayViewInterface {
    override val currentCalendar: Calendar = Calendar.getInstance()

    override fun setCurrentDate(
        year : Int, month : Int,
    ){
        currentCalendar.apply {
            this.set(Calendar.YEAR,year)
            this.set(Calendar.MONTH,month)
        }
    }
}