package com.example.myfooddiarybookaos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myfooddiarybookaos.Dialog.SelectImageFragment
import com.example.myfooddiarybookaos.TabHome.HomeFragment
import com.example.myfooddiarybookaos.TabMyAccount.MyFragment
import com.example.myfooddiarybookaos.TabSearch.SearchFragment
import com.example.myfooddiarybookaos.TabTimeLine.TimeLineFragment
import com.example.myfooddiarybookaos.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 프래그먼트 생성
    var homeFragment : HomeFragment? = null
    var myFragment : MyFragment? = null
    var searchFragment : SearchFragment? = null
    var timeLineFragment : TimeLineFragment? = null
    var transaction: FragmentTransaction? = null // Fragment transaction
    var fragmentManager: FragmentManager? = null // Fragment manager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManager = supportFragmentManager
        transaction = fragmentManager!!.beginTransaction()

        // 프래그먼트 초기화
        initFragment()

        // 하단 탭 초기화
        binding.bottomNavi.setOnItemSelectedListener {
            transaction = fragmentManager!!.beginTransaction()
            when (it.itemId){
                R.id.fragment_1->{
                    transaction?.replace(R.id.main_frame,homeFragment!!)?.commit()
                    true
                }
                R.id.fragment_2->{
                    transaction?.replace(R.id.main_frame,timeLineFragment!!)?.commit()
                    true
                }
                R.id.fragment_3->{
                    transaction?.replace(R.id.main_frame,searchFragment!!)?.commit()
                    true
                }
                R.id.fragment_4->{
                    transaction?.replace(R.id.main_frame,myFragment!!)?.commit()
                    true
                }
                else->{
                    false
                }
            }
        }

        // 클릭 이벤트
        setUpListener()
    }

    private fun initFragment(){
        homeFragment = HomeFragment()
        myFragment = MyFragment()
        searchFragment = SearchFragment()
        timeLineFragment = TimeLineFragment()

        transaction!!.add(R.id.main_frame,homeFragment!!).commit()
    }

    private fun setSubFragment(newFrag : Fragment){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.slide_layout,newFrag)
            ?.addToBackStack(null)
            ?.commit()
        val state =  binding.frameLayout.panelState
        // 닫힌 상태일 경우 열기
        if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
            binding.frameLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        }
        // 열린 상태일 경우 닫기
        else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
            binding.frameLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            transaction
                ?.remove(newFrag)
                ?.commit()
        }
    }

    private fun setUpListener(){
        binding.addButton.setOnClickListener {
            setSubFragment(SelectImageFragment())
        }
    }
}