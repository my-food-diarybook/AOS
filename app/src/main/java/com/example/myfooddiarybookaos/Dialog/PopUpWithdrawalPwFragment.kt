package com.example.myfooddiarybookaos.Dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.databinding.FragmentPopUpWithdrawalPwBinding

class PopUpWithdrawalPwFragment : DialogFragment() {

    private var _binding : FragmentPopUpWithdrawalPwBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopUpWithdrawalPwBinding.inflate(inflater,container,false)
        return binding.root
    }

}