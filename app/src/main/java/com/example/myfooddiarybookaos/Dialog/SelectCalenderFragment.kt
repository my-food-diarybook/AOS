package com.example.myfooddiarybookaos.Dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myfooddiarybookaos.Adapter.CalenderMonthSelectAdapter
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.Model.TabHome.HomeFragment
import com.example.myfooddiarybookaos.databinding.FragmentSelectCalendarBinding

class SelectCalenderFragment : DialogFragment() {
    private var currentYear = 0
    private var selectYear = 0
    private var currentMonth = 0
    private var _binding : FragmentSelectCalendarBinding? = null
    private var mainActivity : MainActivity? =null
    private val binding get() =  _binding!!
    private var adapter : CalenderMonthSelectAdapter? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentMonth = it.getInt("month")
            currentYear = it.getInt("year")
            selectYear = currentYear
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCalendarBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yearText.text = currentYear.toString()
        // 캘린더 뷰 구성 + 동작
        setAdapter()

        // 년도 변경 동작
        adapter?.let {
            binding.leftSideButton.setOnClickListener {
                currentYear-=1
                binding.yearText.text = currentYear.toString()
                setAdapter()
            }
            binding.rightSideButtton.setOnClickListener {
                currentYear+=1
                binding.yearText.text = currentYear.toString()
                setAdapter()
            }
        }

    }

    private fun setAdapter(){
        mainActivity?.let{
            adapter = CalenderMonthSelectAdapter(it,currentYear,currentMonth,selectYear)
            binding.monthRecycler.adapter = adapter
                ?.apply {
                    setOnItemClickListener(object  : CalenderMonthSelectAdapter.OnItemClickListener{
                        override fun itemClick(month: Int) {
                            currentMonth = month
                            (it.supportFragmentManager
                                .findFragmentByTag("homeFrag") as HomeFragment)
                                .setCalenderData(currentYear,currentMonth)

                            this@SelectCalenderFragment.dismissNow()
                        }
                    })
                }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(year: Int?, month: Int?) =
            SelectCalenderFragment().apply {
                arguments = Bundle().apply {
                    if (month != null && year != null) {
                        putInt("month", month)
                        putInt("year", year)
                    }
                }
            }
    }

}