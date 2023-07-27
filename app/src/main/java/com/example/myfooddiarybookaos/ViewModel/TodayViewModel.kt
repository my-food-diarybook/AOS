package com.example.myfooddiarybookaos.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor():ViewModel(),TodayViewModelInterface{
    override var _todayCalendar = MutableLiveData<Calendar>() //오늘 데이터
    override val todayCalendar : LiveData<Calendar> get() = _todayCalendar
    override var _customCalendar = MutableLiveData<CustomCalendar>() // 커스텀 데이터
    override val customCalendar : LiveData<CustomCalendar> get() = _customCalendar
    override var _currentCalendar=  MutableLiveData<Calendar>() //현재 뷰 데이터
    override val currentCalendar: LiveData<Calendar>  get() = _currentCalendar
    init {

        _todayCalendar.value = Calendar.getInstance()
        _currentCalendar.value = Calendar.getInstance()
        _customCalendar.value = CustomCalendar(todayCalendar.value!!.time)
        customCalendar.value!!.initBaseCalendar()
    }

    override fun setCurrentDate(year : Int, month : Int){
        currentCalendar.value?.apply {
            this.set(Calendar.YEAR,year)
            this.set(Calendar.MONTH,month)
            // 커스텀 캘린더 수정
            customCalendar.value?.initData(this.time)
        }
    }
}