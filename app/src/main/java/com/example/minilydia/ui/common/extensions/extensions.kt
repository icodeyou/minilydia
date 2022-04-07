package com.example.minilydia.ui.common.extensions

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
