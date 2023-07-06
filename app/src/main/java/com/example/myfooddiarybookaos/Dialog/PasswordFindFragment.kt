package com.example.myfooddiarybookaos.Dialog

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myfooddiarybookaos.Login.LoginActivity
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.databinding.FragmentFindPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PasswordFindFragment : DialogFragment() {
    private var _binding : FragmentFindPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindPasswordBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){

        // 텍스트 색상 변경
        val spannableString = SpannableString(binding.comment.text)
        val startIndex = spannableString.indexOf("5")

        if (startIndex>=0){
            spannableString.setSpan(
                ForegroundColorSpan(Color.parseColor("#FC6262"))
                ,startIndex
                ,startIndex+1
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.comment.text = spannableString
        }
    }

}