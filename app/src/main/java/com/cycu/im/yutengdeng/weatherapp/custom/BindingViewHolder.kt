package com.cycu.im.yutengdeng.weatherapp.custom

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.cycu.im.yutengdeng.weatherapp.BR

class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any) {
        binding.setVariable(BR.obj, obj)
        binding.executePendingBindings()
    }
}