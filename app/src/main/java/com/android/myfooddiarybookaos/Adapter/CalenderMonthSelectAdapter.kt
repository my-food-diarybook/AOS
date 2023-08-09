package com.android.myfooddiarybookaos.Adapter
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.android.myfooddiarybookaos.MainActivity
//import com.android.myfooddiarybookaos.R
//import com.android.myfooddiarybookaos.databinding.ItemMonthDataBinding
//import java.util.Calendar
//
//class CalenderMonthSelectAdapter(
//    private val mainActivity: MainActivity,
//    private var currentYear : Int,
//    private var currentMonth : Int,
//    private var selectYear : Int
//) : RecyclerView.Adapter<CalenderMonthSelectAdapter.ViewHolder>() {
//    private var dataSet  = ArrayList<Int>()
//    private var onItemClickListener : OnItemClickListener? = null
//    private var todayDate:Int = 0 //오늘 치환 날짜
//    private var currentDate : Int = 0 // 현재 뷰 치환 날짜
//    private var currentSelectDate : Int = 0 // 현재 선택 치환 날짜
//    init {
//        val calendarDate = Calendar.getInstance()
//        todayDate = (calendarDate.get(Calendar.YEAR))*12+calendarDate.get(Calendar.MONTH)+1
//        currentDate = currentYear*12+currentMonth
//        currentSelectDate = selectYear*12 + currentMonth
//    }
//    interface OnItemClickListener{
//        fun itemClick(month : Int)
//    }
//    fun setOnItemClickListener(listener:OnItemClickListener){
//        this.onItemClickListener = listener
//    }
//    init { for (i in 1..12){ dataSet.add(i) } }
//
//    private lateinit var binding: ItemMonthDataBinding
//
//    inner class ViewHolder(binding: ItemMonthDataBinding)
//        : RecyclerView.ViewHolder(binding.root) {
//            @SuppressLint("SetTextI18n")
//            fun hold(){
//                // 년 , 월을 비교하기 위해서 데이터 치환
//                val viewDate = currentYear*12+dataSet[absoluteAdapterPosition]
//
//                binding.textId.text = "${dataSet[absoluteAdapterPosition]}월"
//
//                // 지난+ 현재  데이터만 클릭 가능
//                if (viewDate<=todayDate) {
//                    binding.root.setOnClickListener {
//                        onItemClickListener?.itemClick(dataSet[absoluteAdapterPosition])
//                    }
//                }
//                when{
//                    viewDate<=todayDate -> binding.prevMonthText()
//                    else -> binding.nextMonthText()
//                }
//                if (viewDate ==currentDate && viewDate ==currentSelectDate) binding.currentMonthText()
//
//            }
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        binding = ItemMonthDataBinding.inflate(LayoutInflater.from(mainActivity))
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = dataSet.size
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.hold()
//    }
//
//
//    private fun ItemMonthDataBinding.prevMonthText() =
//        binding.textId.setTextColor(ContextCompat.getColor(mainActivity,R.color.black))
//    @SuppressLint("ResourceAsColor")
//    private fun ItemMonthDataBinding.currentMonthText() =
//        binding.textId.setTextColor(ContextCompat.getColor(mainActivity,R.color.main_color))
//    @SuppressLint("ResourceAsColor")
//    private fun ItemMonthDataBinding.nextMonthText() =
//        binding.textId.setTextColor(ContextCompat.getColor(mainActivity,R.color.calender_next_color))
//}