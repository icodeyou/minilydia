package com.example.minilydia.ui.common

import android.widget.Toast
import com.example.minilydia.MyApplication

fun showLong(text: String) {
    Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_LONG).show()
}

fun showShort(text: String) {
    Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_LONG).show()
}