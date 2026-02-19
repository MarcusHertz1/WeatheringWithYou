package ru.netology.weatheringwithyou.ui.screens.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.netology.weatheringwithyou.data.DataStoreManager
import ru.netology.weatheringwithyou.domain.WeatherRepository
import ru.netology.weatheringwithyou.ui.appBase.BaseViewModel
import ru.netology.weatheringwithyou.ui.appBase.UiEvent
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    val weatherRepository: WeatherRepository
) : BaseViewModel<MainState, UiEvent, MainActions>() {
    override fun createInitialState(): MainState = MainState()

    init {
        viewModelScope.launch {
            dataStoreManager.appState.collect {
                updateState {
                    copy(
                        selectedCity = it.city,
                    )
                }
                loadWeather()
            }
        }
    }

    override fun applyAction(action: MainActions) {
        when (action) {
            MainActions.LoadWeather -> loadWeather()
        }
    }

    var weatherJob: Job? = null
    private fun loadWeather() {
        updateState { copy(isLoading = true, error = null) }
        weatherJob?.cancel()
        weatherJob = viewModelScope.launch {
            try {
                val weatherData =
                    state.value.selectedCity.let { weatherRepository.getWeather(it.cityForApi) }
                updateState {
                    copy(
                        weatherData = weatherData,
                        isLoading = false,
                    )
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                updateState { copy(error = e.message, isLoading = false) }
            }
        }

    }
}