package com.example.nijatahmadli.blockchainwallet.presentation.util

import android.text.format.DateFormat
import java.util.*
import javax.inject.Inject

class DateUtil @Inject constructor() {

    fun getDate(time: Long): String {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time * 1000
        return DateFormat.format("dd-MM-yyyy HH:mm:ss", cal).toString()
    }
}