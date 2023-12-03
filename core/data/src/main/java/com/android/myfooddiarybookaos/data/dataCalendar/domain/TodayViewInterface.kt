package com.android.myfooddiarybookaos.data.todayViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

interface TodayViewInterface {
    val _observeData: MutableLiveData<Boolean>
    val observeData: LiveData<Boolean>
    val currentCalendar: Calendar
    fun setCurrentDate(year: Int, month: Int)
    fun dataChangeOn()
}

//for preview
//class FakeTodayView : TodayViewInterface {
//    override var dataChange: Boolean = false
//    override val currentCalendar: Calendar = Calendar.getInstance()
//    override fun setCurrentDate(year: Int, month: Int) {}
//    override fun dataChangeOn() {}
//    override fun dataChangeOff() {}
//}
