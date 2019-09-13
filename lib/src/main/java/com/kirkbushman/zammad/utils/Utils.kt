package com.kirkbushman.zammad.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun convertStringToDate(dateStr: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.parse(dateStr.replace('T', ' ').replace('Z', ' '))
    }
}
