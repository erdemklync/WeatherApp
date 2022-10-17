package com.ekalyoncu.weatherapp.data

import com.google.gson.Gson

data class DetailInfo(
    val timezone: String,
    val daily: Daily,
){
    fun toJson() : String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String) : DetailInfo {
            return Gson().fromJson(json, DetailInfo::class.java)
        }
    }
}
