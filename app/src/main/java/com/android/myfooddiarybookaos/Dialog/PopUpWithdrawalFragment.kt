package com.android.myfooddiarybookaos.Dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.android.myfooddiarybookaos.MainActivity
import com.android.myfooddiarybookaos.databinding.FragmentPopUpWithdrawalBinding


class PopUpWithdrawalFragment : DialogFragment() {
    private var _binding : FragmentPopUpWithdrawalBinding?= null
    private val binding get() = _binding!!
    private var mainActivity : MainActivity? = null
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
        _binding = FragmentPopUpWithdrawalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeButton.setOnClickListener {
            this@PopUpWithdrawalFragment.dismissNow()
        }

        binding.okButton.setOnClickListener {
            this@PopUpWithdrawalFragment.dismissNow()
            mainActivity?.let {
                PopUpWithdrawalPwFragment()
                    .show(it.supportFragmentManager,"")
            }
        }
    }


}