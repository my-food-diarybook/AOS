package com.example.myfooddiarybookaos.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfooddiarybookaos.MainActivity
import com.example.myfooddiarybookaos.databinding.ItemMonthDataBinding

class CalendarMonthSelectAdapter(
    private val mainActivity: MainActivity
) : RecyclerView.Adapter<CalendarMonthSelectAdapter.ViewHolder>() {
    private var dataSet  = ArrayList<Int>()
    init { for (i in 1..12){ dataSet.add(i) } }

    private lateinit var binding: ItemMonthDataBinding

    inner class ViewHolder(private val binding: ItemMonthDataBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMonthDataBinding.inflate(LayoutInflater.from(mainActivity))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.textId.text = "${dataSet[position]}월"
    }
}