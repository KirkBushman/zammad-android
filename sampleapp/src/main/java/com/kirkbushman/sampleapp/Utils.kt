package com.kirkbushman.sampleapp

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String, long: Boolean = false) {
    Toast.makeText(this, text, if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}
