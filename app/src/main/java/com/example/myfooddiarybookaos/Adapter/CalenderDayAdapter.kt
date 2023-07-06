package com.example.myfooddiarybookaos.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import com.example.myfooddiarybookaos.databinding.ItemDayTextBinding
import java.util.*
import javax.inject.Inject


class CalenderDayAdapter(
  private val mainActivity: MainActivity,
  private val date: Date,
  private val calendarLayout : LinearLayout // 사이즈 용
) : RecyclerView.Adapter<CalenderDayAdapter.ViewHolder>() {
    private lateinit var binding: ItemDayTextBinding
    private var customCalendar : CustomCalendar =
        CustomCalendar(date)
    init {
        customCalendar.initBaseCalendar()


    }
    inner class ViewHolder(binding: ItemDayTextBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun hold(){
                val date = customCalendar.dateList[absoluteAdapterPosition]
                if (date.day==0) {
                    binding.dayText.text = ""
                }
                else {
                    // 높이와 너비 설정
                    val params = LinearLayout.LayoutParams(
                        calendarLayout.width / 7, calendarLayout.height / 6
                    )
                    binding.root.layoutParams = params

                    when (date.isSelected) {
                        0 -> binding.setTodayIcon()
                        1 -> binding.setTextColor()
                        else -> binding.setNormalTextColor()
                    }
                    binding.dayText.text = date.day.toString()
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemDayTextBinding.inflate(LayoutInflater.from(mainActivity))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = customCalendar.dateList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hold()
    }

    @SuppressLint("ResourceAsColor")
    private fun ItemDayTextBinding.setTextColor()=
        binding.dayText.setTextColor(ContextCompat.getColor(mainActivity,R.color.line_color_deep))
    private fun ItemDayTextBinding.setNormalTextColor() =
        binding.dayText.setTextColor(ContextCompat.getColor(mainActivity,R.color.color_day_of_weak))
    private fun ItemDayTextBinding.setTodayIcon() {
        binding.viewToday.visibility = View.VISIBLE
    }
}