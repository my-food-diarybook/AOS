package com.example.myfooddiarybookaos.TabHome

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfooddiarybookaos.Adapter.CalenderDayAdapter
import com.example.myfooddiarybookaos.Dialog.SelectCalenderFragment
import com.example.myfooddiarybookaos.Login.LoginActivity
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding?  = null
    private val binding  get() = _binding!!
    private var mainActivity: MainActivity? = null
    private var calendarDate : Calendar? = null
    private var dayAdapter : CalenderDayAdapter? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarDate =  Calendar.getInstance()
        initView()
        setUpListener()

    }

    private fun setUpListener(){
        binding.calendarBox.calendarButton.setOnClickListener {
            mainActivity?.let {
                val selectCalendarFragment = SelectCalenderFragment.newInstance(
                    calendarDate?.get(Calendar.YEAR),
                    calendarDate?.get(Calendar.MONTH)?.plus(1)
                )
                selectCalendarFragment.show(
                    it.supportFragmentManager,""
                )
            }
        }

        binding.testLogin.setOnClickListener {
            val intent = Intent(mainActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView(){

        setAdapter()
        setCalendarText()
    }

    @SuppressLint("SetTextI18n")
    private fun setCalendarText(){
        binding.calendarBox.calenderText.text =
            "${calendarDate?.get(Calendar.YEAR)}.${calendarDate?.get(Calendar.MONTH)?.plus(1)}"
    }

    private fun setAdapter(){
        mainActivity?.let{content->
            calendarDate?.let {
                dayAdapter = CalenderDayAdapter(content, it.time,binding.calendarLayout.calendarLinear)
                binding.calendarLayout.monthRecycler.adapter = dayAdapter
            }
        }
    }

    fun setCalenderData(year:Int,month:Int){
        calendarDate?.let{
            it.set(Calendar.YEAR,year)
            it.set(Calendar.MONTH,month-1)
        }
        setAdapter()
        setCalendarText()
    }
}