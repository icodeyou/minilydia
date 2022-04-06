package com.example.minilydia.ui.common

import android.widget.Toast
import com.example.minilydia.AndroidApplication

fun showLong(text: String) {
    Toast.makeText(AndroidApplication.getContext(), text, Toast.LENGTH_LONG).show()
}

fun showShort(text: String) {
    Toast.makeText(AndroidApplication.getContext(), text, Toast.LENGTH_LONG).show()
}