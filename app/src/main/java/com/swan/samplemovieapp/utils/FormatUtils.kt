package com.swan.samplemovieapp.utils

import java.text.SimpleDateFormat
import java.util.*

object FormatUtils {

    fun formattedDate(inputDate: String) : String{
        val inputPattern = "yyyy-MM-dd"
        val outputPattern = "MMM dd, yyyy"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

        var formattedDateTime = ""

        try {
            val date = inputFormat.parse(inputDate)
            if (date != null) {
                formattedDateTime = outputFormat.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return formattedDateTime
    }
}