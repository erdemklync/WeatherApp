package com.ekalyoncu.weatherapp.service

import com.ekalyoncu.weatherapp.data.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/onecall?")
    fun getWeatherInfo(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("exclude")
        exclude: String,
        @Query("units")
        units: String,
        @Query("lang")
        lang: String,
    ): Call<WeatherResponse>
}