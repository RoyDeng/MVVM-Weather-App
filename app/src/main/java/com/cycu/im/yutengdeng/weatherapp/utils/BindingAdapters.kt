package com.cycu.im.yutengdeng.weatherapp.utils

import android.databinding.BindingAdapter
import android.widget.TextView

object BindingAdapters {

    @BindingAdapter("android:drawableEnd")
    fun drawableEnd(view: TextView, resourceId: Int) {
        view.setCompoundDrawablesWithIntrinsicBounds(0, 0, resourceId, 0)
    }
}