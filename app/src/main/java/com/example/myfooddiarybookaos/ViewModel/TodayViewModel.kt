package com.example.myfooddiarybookaos.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfooddiarybookaos.Model.TabHome.CustomCalendar
import java.util.Calendar

class TodayViewModel :ViewModel(){
    private val _todayCalendar = MutableLiveData<Calendar>()
    val todayCalendar : LiveData<Calendar> get() = _todayCalendar

    private val _customCalendar = MutableLiveData<CustomCalendar>()
    val customCalendar : LiveData<CustomCalendar> get() = _customCalendar
    init {
        _todayCalendar.value = Calendar.getInstance()
        _customCalendar.value = todayCalendar.value?.let {
            CustomCalendar(it.time)
        }
        _customCalendar.value?.initBaseCalendar()
    }
}