package com.android.myfooddiarybookaos.data.todayViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

interface TodayViewModelInterface{
    var _todayCalendar :MutableLiveData<Calendar>
    var _currentCalendar : MutableLiveData<Calendar>
    val todayCalendar :LiveData<Calendar>
    val currentCalendar : LiveData<Calendar>
    val dataChanger : MutableLiveData<Boolean>
    fun setCurrentDate(year:Int,month:Int)
}

//for preview
class FakeTodayViewModel : TodayViewModelInterface {
    override var _todayCalendar: MutableLiveData<Calendar> =
        MutableLiveData<Calendar>()
    override var _currentCalendar : MutableLiveData<Calendar> =
        MutableLiveData<Calendar>()
    override val todayCalendar: LiveData<Calendar>
        get() = _todayCalendar
    override val currentCalendar : LiveData<Calendar>
        get() = _currentCalendar
    override val dataChanger = MutableLiveData<Boolean>()
    override fun setCurrentDate(year: Int, month: Int) {}

    init {
        _todayCalendar.value =Calendar.getInstance()
        _currentCalendar.value = todayCalendar.value
    }
}
