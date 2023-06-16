package com.example.myfooddiarybookaos.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfooddiarybookaos.Dialog.PasswordChangeFragment
import com.example.myfooddiarybookaos.Dialog.PasswordFindFragment
import com.example.myfooddiarybookaos.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding :  ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()
    }

    private fun setUpListener(){
        binding.test2.setOnClickListener {
            PasswordChangeFragment().show(
                this@LoginActivity.supportFragmentManager,""
            )
        }

        binding.test1.setOnClickListener {
            PasswordFindFragment().show(
                this@LoginActivity.supportFragmentManager,""
            )
        }
    }
}