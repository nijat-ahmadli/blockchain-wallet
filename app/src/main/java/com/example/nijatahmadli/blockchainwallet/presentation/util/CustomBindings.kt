package com.example.nijatahmadli.blockchainwallet.presentation.util

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}