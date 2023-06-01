package com.example.myfooddiarybookaos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfooddiarybookaos.TabHome.HomeFragment
import com.example.myfooddiarybookaos.TabMyAccount.MyFragment
import com.example.myfooddiarybookaos.TabSearch.SearchFragment
import com.example.myfooddiarybookaos.TabTimeLine.TimeLineFragment
import com.example.myfooddiarybookaos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 프래그먼트 생성
    var homeFragment : HomeFragment? = null
    var myFragment : MyFragment? = null
    var searchFragment : SearchFragment? = null
    var timeLineFragment : TimeLineFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}