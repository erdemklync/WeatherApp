package com.ekalyoncu.weatherapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun TextView.setWeatherDegree(degree: Double) {
    val formattedDegree = "%.1f".format(degree) + "°C"
    this.text = formattedDegree
}

fun ImageView.setWeatherImage(
    weatherCode: String,
) {
    val weatherIconId = WeatherIcons.getWeatherIcon(weatherCode)
    val weatherIconDrawable = ResourcesCompat.getDrawable(resources, weatherIconId, context.theme)
    this.setImageDrawable(weatherIconDrawable)
}

fun TextView.setWeatherDescription(
    description: String,
) {
    this.text = description
}

fun TextView.setDate(epochTime: Int) {
    val timeStamp = Timestamp(epochTime.toLong())
    val date = Date(timeStamp.time * 1000)
    this.text = SimpleDateFormat("dd.MM.yyyy", Locale("tr")).format(date)
}

fun TextView.setDayName(epochTime: Int) {
    val timeStamp = Timestamp(epochTime.toLong())
    val date = Date(timeStamp.time * 1000)
    this.text = SimpleDateFormat("EEEE", Locale("tr")).format(date)
}