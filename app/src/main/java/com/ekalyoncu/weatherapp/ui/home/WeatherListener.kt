package com.ekalyoncu.weatherapp.ui.home

import com.ekalyoncu.weatherapp.data.Daily

interface WeatherListener {
    fun onClick(daily: Daily)
}