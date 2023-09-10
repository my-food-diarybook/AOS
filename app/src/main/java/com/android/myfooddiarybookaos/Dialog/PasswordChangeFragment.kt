package com.android.myfooddiarybookaos.Dialog

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.android.myfooddiarybookaos.databinding.FragmentChangePasswordBinding


class PasswordChangeFragment : DialogFragment() {
    private var _binding : FragmentChangePasswordBinding? = null
    private val binding get()= _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        // 텍스트 색상 변경
        val spannableString = SpannableString(binding.comment.text)
        val startIndex = spannableString.indexOf("90일")

        if (startIndex>=0){
            spannableString.setSpan(
                ForegroundColorSpan(Color.parseColor("#FC6262"))
                ,startIndex
                ,startIndex+3
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.comment.text = spannableString
        }

    }

}