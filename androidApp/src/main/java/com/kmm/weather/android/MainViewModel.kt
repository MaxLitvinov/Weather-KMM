package com.kmm.weather.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmm.weather.InteractorProvider
import com.kmm.weather.home_page.HomePageInteractor
import com.kmm.weather.home_page.HomePageUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val interactor: HomePageInteractor = InteractorProvider.homePageInteractor

    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.Loading)
    val uiState: StateFlow<HomePageUiState> = _uiState

    fun fetchWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = interactor.fetchWeather()
        }
    }

    fun retry() {
        _uiState.value = HomePageUiState.Loading
        fetchWeather()
    }
}
