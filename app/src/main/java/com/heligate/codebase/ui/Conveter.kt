package com.heligate.codebase.ui

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView


object BindingAdapters {

    @BindingAdapter("app:orderStatus")
    @JvmStatic fun setOrderStatus(textView: TextView, status: String) {
        Log.d("AnhVM", "status: $status")
        if (status == "1") {
            textView.setTextColor(ContextCompat.getColor(textView.context, android.R.color.holo_red_dark))
        } else {
            textView.setTextColor(ContextCompat.getColor(textView.context, android.R.color.holo_blue_dark))
        }

    }

}