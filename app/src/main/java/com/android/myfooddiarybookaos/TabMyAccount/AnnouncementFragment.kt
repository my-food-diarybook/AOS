package com.android.myfooddiarybookaos.TabMyAccount
//
//import android.content.Context
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.android.myfooddiarybookaos.MainActivity
//import com.android.myfooddiarybookaos.databinding.FragmentAnnouncementBinding
//
//class AnnouncementFragment : Fragment() {
//    private var mainActivity : MainActivity? =null
//    private var _binding : FragmentAnnouncementBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mainActivity = context as MainActivity
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentAnnouncementBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.backButton.setOnClickListener {
//            mainActivity?.mainFrameGoBack(this@AnnouncementFragment)
//        }
//    }
//}