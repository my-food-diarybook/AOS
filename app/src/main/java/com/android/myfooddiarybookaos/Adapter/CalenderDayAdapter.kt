package com.android.myfooddiarybookaos.Adapter
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.android.myfooddiarybookaos.MainActivity
//import com.android.myfooddiarybookaos.R
//import com.android.myfooddiarybookaos.home.CustomCalendar
//import com.android.myfooddiarybookaos.databinding.ItemDayTextBinding
//import java.util.*

//
//class CalenderDayAdapter(
//  private val mainActivity: MainActivity,
//  private val date: Date,
//  private val calendarLayout : LinearLayout // 사이즈 용
//) : RecyclerView.Adapter<CalenderDayAdapter.ViewHolder>() {
//    private lateinit var binding: ItemDayTextBinding
//    var customCalendar : com.android.myfooddiarybookaos.home.CustomCalendar =
//        com.android.myfooddiarybookaos.home.CustomCalendar()
//    init {
//        customCalendar.initBaseCalendar()
//
//
//    }
//    inner class ViewHolder(binding: ItemDayTextBinding)
//        : RecyclerView.ViewHolder(binding.root) {
//            fun hold(){
//                val date = customCalendar.dateSet[absoluteAdapterPosition]
//                if (date.day==0) {
//                    binding.dayText.text = ""
//                }
//                else {
//                    // 높이와 너비 설정
//                    val params = LinearLayout.LayoutParams(
//                        calendarLayout.width / 7, calendarLayout.height / 6
//                    )
//                    binding.root.layoutParams = params
//
//                    when (date.isSelected) {
//                        0 -> binding.setTodayIcon()
//                        1 -> binding.setTextColor()
//                        else -> binding.setNormalTextColor()
//                    }
//                    binding.dayText.text = date.day.toString()
//                }
//            }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        binding = ItemDayTextBinding.inflate(LayoutInflater.from(mainActivity))
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = customCalendar.dateSet.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.hold()
//    }
//
//    @SuppressLint("ResourceAsColor")
//    private fun ItemDayTextBinding.setTextColor()=
//        binding.dayText.setTextColor(ContextCompat.getColor(mainActivity,R.color.line_color_deep))
//    private fun ItemDayTextBinding.setNormalTextColor() =
//        binding.dayText.setTextColor(ContextCompat.getColor(mainActivity,R.color.color_day_of_weak))
//    private fun ItemDayTextBinding.setTodayIcon() {
//        binding.viewToday.visibility = View.VISIBLE
//    }
//}