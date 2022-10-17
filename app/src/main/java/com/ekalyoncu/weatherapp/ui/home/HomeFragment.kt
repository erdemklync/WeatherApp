package com.ekalyoncu.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekalyoncu.weatherapp.R
import com.ekalyoncu.weatherapp.data.Daily
import com.ekalyoncu.weatherapp.data.DetailInfo
import com.ekalyoncu.weatherapp.data.WeatherResponse
import com.ekalyoncu.weatherapp.databinding.FragmentHomeBinding
import com.ekalyoncu.weatherapp.util.setDate
import com.ekalyoncu.weatherapp.util.setWeatherDegree
import com.ekalyoncu.weatherapp.util.setWeatherDescription
import com.ekalyoncu.weatherapp.util.setWeatherImage

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            setWeatherInfo(state.weatherResponse)

            binding.content.isVisible = state.homeUiState is HomeUiState.Loaded
            binding.imageLoading.isVisible = state.homeUiState is HomeUiState.Loading
            binding.textLoading.isVisible = state.homeUiState is HomeUiState.Loading
        }
        return binding.root
    }

    private fun setWeatherInfo(weatherResponse: WeatherResponse) {
        with(binding) {
            with(weatherResponse) {
                textCityName.text = timezone
                textDate.setDate(current.dt)
                textTemperature.setWeatherDegree(current.temp)
                imageWeather.setWeatherImage(current.weather[0].icon)
                textWeatherDescription.setWeatherDescription(current.weather[0].description)

                buttonSeeDetails.setOnClickListener {
                    val detailInfo = DetailInfo(timezone, weatherResponse.daily[0])
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(detailInfo.toJson())
                    findNavController().navigate(action)
                }

                recyclerviewNextDays.apply {
                    adapter = WeatherAdapter(
                        response = weatherResponse.daily,
                        listener = object : WeatherListener {
                            override fun onClick(daily: Daily) {
                                val detailInfo = DetailInfo(timezone, daily)

                                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(detailInfo.toJson())
                                findNavController().navigate(action)
                            }
                        }
                    )
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}