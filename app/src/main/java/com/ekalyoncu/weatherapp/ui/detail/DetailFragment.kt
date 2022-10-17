package com.ekalyoncu.weatherapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ekalyoncu.weatherapp.data.DetailInfo
import com.ekalyoncu.weatherapp.databinding.FragmentDetailBinding
import com.ekalyoncu.weatherapp.util.setDate
import com.ekalyoncu.weatherapp.util.setWeatherDegree
import com.ekalyoncu.weatherapp.util.setWeatherImage

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.detailLiveData.observe(viewLifecycleOwner) { info ->
            val detailInfo = DetailInfo.fromJson(info)
            setDailyInfo(detailInfo)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun setDailyInfo(detailInfo: DetailInfo) {
        with(binding) {
            with(detailInfo) {
                textCityName.text = timezone
                textDate.setDate(daily.dt)
                imageWeather.setWeatherImage(daily.weather[0].icon)
                textMorningValue.setWeatherDegree(daily.temp.day)
                textEveningValue.setWeatherDegree(daily.temp.eve)
                textNightValue.setWeatherDegree(daily.temp.night)
                textHumidityValue.text = daily.humidity.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}