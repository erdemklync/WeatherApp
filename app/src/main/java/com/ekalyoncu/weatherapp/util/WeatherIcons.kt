package com.ekalyoncu.weatherapp.util

import com.ekalyoncu.weatherapp.R

class WeatherIcons {

    companion object {
        private val weatherIcons = mapOf(
            "01d" to R.drawable.img_01d,
            "02d" to R.drawable.img_02d,
            "03d" to R.drawable.img_03d,
            "04d" to R.drawable.img_03d,
            "09d" to R.drawable.img_09d,
            "10d" to R.drawable.img_10d,
            "11d" to R.drawable.img_11d,
            "13d" to R.drawable.img_13d,
            "50d" to R.drawable.img_50d,
        )

        fun getWeatherIcon(code: String): Int = weatherIcons.getOrElse(code) { R.drawable.img_03d }
    }
}
