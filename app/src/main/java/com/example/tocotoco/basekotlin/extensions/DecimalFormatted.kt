package com.example.tocotoco.basekotlin.extensions

import java.text.DecimalFormat

fun Int.decimalFormatted(): String = DecimalFormat("#,###").format(this)
