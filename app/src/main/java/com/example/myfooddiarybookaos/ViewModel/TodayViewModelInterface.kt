package com.example.myfooddiarybookaos.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import java.util.*

interface TodayViewModelInterface{
    var _todayCalendar :MutableLiveData<Calendar>
    var _currentCalendar : MutableLiveData<Calendar>
    var _customDataList : MutableLiveData<CustomCalendar>
    val todayCalendar :LiveData<Calendar>
    val currentCalendar : LiveData<Calendar>
    val customCalendar : LiveData<CustomCalendar>
    fun setCurrentDate(year:Int,month:Int)
    fun setCustomCalendar()
}

//for preview
class FakeTodayViewModel : TodayViewModelInterface{
    override var _todayCalendar: MutableLiveData<Calendar> =
        MutableLiveData<Calendar>()
    override var _customDataList : MutableLiveData<CustomCalendar> =
        MutableLiveData<CustomCalendar>()
    override var _currentCalendar : MutableLiveData<Calendar> =
        MutableLiveData<Calendar>()
    override val todayCalendar: LiveData<Calendar>
        get() = _todayCalendar
    override val currentCalendar : LiveData<Calendar>
        get() = _currentCalendar
    override val customCalendar : LiveData<CustomCalendar>
        get() = _customDataList
    override fun setCurrentDate(year: Int, month: Int) {}
    override fun setCustomCalendar() {}

    init {
        _todayCalendar.value =Calendar.getInstance()
        _currentCalendar.value = todayCalendar.value
        _customDataList.value = CustomCalendar()
    }
}
