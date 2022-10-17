package com.ekalyoncu.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekalyoncu.weatherapp.data.Daily
import com.ekalyoncu.weatherapp.databinding.ItemWeatherBinding
import com.ekalyoncu.weatherapp.util.setDate
import com.ekalyoncu.weatherapp.util.setDayName
import com.ekalyoncu.weatherapp.util.setWeatherDegree
import com.ekalyoncu.weatherapp.util.setWeatherImage

class WeatherAdapter(
    private val response: List<Daily>,
    val listener: WeatherListener,
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: Daily) {
            with(binding) {
                root.setOnClickListener {
                    listener.onClick(daily)
                }

                textDailyTitle.setDayName(daily.dt)
                textDailyDate.setDate(daily.dt)
                textDailyTemperature.setWeatherDegree(daily.temp.day)
                imageDailyWeather.setWeatherImage(daily.weather[0].icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val daily: Daily = response[position]
        holder.bind(daily)
    }

    override fun getItemCount(): Int = response.size
}