package com.example.myfooddiarybookaos.Dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.databinding.FragmentSelectCalendarBinding

class SelectCalendarFragment : DialogFragment() {
    private var currentMonth = 0
    private var currentYear = 0
    private var _binding : FragmentSelectCalendarBinding? = null
    private val binding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentMonth = it.getInt("month")
            currentYear = it.getInt("year")
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
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(month: Int, year: Int) =
//            SelectCalendarFragment().apply {
//                arguments = Bundle().apply {
//                    putInt("month", month)
//                    putInt("year", year)
//                }
//            }
//    }
}