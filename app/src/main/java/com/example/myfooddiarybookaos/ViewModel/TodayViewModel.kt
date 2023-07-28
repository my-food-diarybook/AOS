package com.example.myfooddiarybookaos.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor():ViewModel(),TodayViewModelInterface{

    override var _todayCalendar = MutableLiveData<Calendar>() //오늘 데이터
    override val todayCalendar : LiveData<Calendar> get() = _todayCalendar
    override var _currentCalendar=  MutableLiveData<Calendar>() //현재 뷰 데이터
    override val currentCalendar: LiveData<Calendar>  get() = _currentCalendar
    override var _customDataList: MutableLiveData<CustomCalendar> = MutableLiveData<CustomCalendar>()
    override val customCalendar: MutableLiveData<CustomCalendar> get() =_customDataList
    init {

        _todayCalendar.value = Calendar.getInstance()
        _currentCalendar.value = Calendar.getInstance()
        _customDataList.value = CustomCalendar()
    }

    override fun setCurrentDate(year : Int, month : Int){
        currentCalendar.value?.apply {
            this.set(Calendar.YEAR,year)
            this.set(Calendar.MONTH,month)
        }
    }

    override fun setCustomCalendar() {
        currentCalendar.value?.apply {
            customCalendar.value?.initData(this.time)
        }
    }
}