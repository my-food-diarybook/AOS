package com.example.myfooddiarybookaos.TabMyAccount

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.databinding.FragmentMyBinding
import org.mozilla.javascript.tools.jsc.Main

class MyFragment : Fragment() {
    private var _binding : FragmentMyBinding? = null
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
        _binding = FragmentMyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener(){
        binding.myInfoButton.setOnClickListener {
            mainActivity?.mainFrameChange(MyInfoFragment())
        }
        binding.announcement.setOnClickListener {
            mainActivity?.mainFrameChange(AnnouncementFragment())
        }
    }

}