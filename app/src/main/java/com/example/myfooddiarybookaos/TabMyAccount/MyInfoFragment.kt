package com.example.myfooddiarybookaos.TabMyAccount

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfooddiarybookaos.Dialog.PasswordChangeFragment
import com.example.myfooddiarybookaos.Dialog.PopUpDeleteFragment
import com.example.myfooddiarybookaos.Dialog.PopUpWithdrawalFragment
import com.example.myfooddiarybookaos.Login.LoginActivity
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.databinding.FragmentMyInfoBinding
import org.mozilla.javascript.tools.jsc.Main

class MyInfoFragment : Fragment() {
    private var mainActivity : MainActivity? = null
    private var _binding : FragmentMyInfoBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentMyInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener(){
        binding.backButton.setOnClickListener {
            mainActivity?.mainFrameGoBack(this@MyInfoFragment)
        }
        binding.myTab1.setOnClickListener {// 비밀번호 변경
            mainActivity?.let{

            }
        }
        binding.myTab2.setOnClickListener {// 모든 식사일기 삭제
            mainActivity?.let {
                PopUpDeleteFragment()
                    .show(it.supportFragmentManager,"")
            }
        }
        binding.myTab3.setOnClickListener {// 회원 탈퇴
            mainActivity?.let {
                PopUpWithdrawalFragment()
                    .show(it.supportFragmentManager,"")
            }
        }
        binding.myTab4.setOnClickListener {// 로그아웃
            mainActivity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}