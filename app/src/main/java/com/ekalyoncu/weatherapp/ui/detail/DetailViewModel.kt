package com.ekalyoncu.weatherapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ekalyoncu.weatherapp.util.Constants

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val detailLiveData = savedStateHandle.getLiveData<String>(Constants.DETAIL_INFO_KEY)

}