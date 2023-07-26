package com.example.myfooddiarybookaos.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import java.util.Calendar

interface TodayViewModelInterface{
    var _todayCalendar :MutableLiveData<Calendar>
    var _customCalendar : MutableLiveData<CustomCalendar>
    var _currentCalendar : MutableLiveData<Calendar>
    val todayCalendar :LiveData<Calendar>
    val customCalendar : LiveData<CustomCalendar>
    val currentCalendar : LiveData<Calendar>

}

//for preview
class FakeTodayViewModel : TodayViewModelInterface{
    override var _todayCalendar: MutableLiveData<Calendar> =
        MutableLiveData<Calendar>()
    override var _customCalendar: MutableLiveData<CustomCalendar> =
        MutableLiveData<CustomCalendar>()
    override var _currentCalendar : MutableLiveData<Calendar> =
        MutableLiveData<Calendar>()
    override val todayCalendar: LiveData<Calendar>
        get() = _todayCalendar
    override val customCalendar: LiveData<CustomCalendar>
        get() = _customCalendar
    override val currentCalendar : LiveData<Calendar>
        get() = _currentCalendar

    init {
        _todayCalendar.value =Calendar.getInstance()
        _currentCalendar.value = todayCalendar.value
        _customCalendar.value = CustomCalendar(todayCalendar.value!!.time)
        customCalendar.value!!.initBaseCalendar()
    }
}
