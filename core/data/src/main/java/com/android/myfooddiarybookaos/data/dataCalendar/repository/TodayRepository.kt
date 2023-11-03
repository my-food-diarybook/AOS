package com.android.myfooddiarybookaos.data.dataCalendar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface
import java.util.*
import javax.inject.Inject

class TodayRepository @Inject constructor(

) : TodayViewModelInterface {
    override var _todayCalendar = MutableLiveData<Calendar>() //오늘 데이터
    override val todayCalendar : LiveData<Calendar> get() = _todayCalendar
    override var _currentCalendar=  MutableLiveData<Calendar>() //현재 뷰 데이터
    override val currentCalendar: LiveData<Calendar> get() = _currentCalendar
    override val dataChanger = MutableLiveData<Boolean>()
    init {
        _todayCalendar.value = Calendar.getInstance()
        _currentCalendar.value = Calendar.getInstance()
        dataChanger.value= true
    }
    override fun setCurrentDate(year : Int, month : Int){
        currentCalendar.value?.apply {
            this.set(Calendar.YEAR,year)
            this.set(Calendar.MONTH,month)
        }
        // 뷰 리셋
        dataChanger.value= false
        dataChanger.value= true
    }
}