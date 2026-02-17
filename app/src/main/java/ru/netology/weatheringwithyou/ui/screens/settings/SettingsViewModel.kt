package ru.netology.weatheringwithyou.ui.screens.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.netology.weatheringwithyou.data.DataStoreManager
import ru.netology.weatheringwithyou.ui.appBase.BaseViewModel
import ru.netology.weatheringwithyou.ui.appBase.UiEvent
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
): BaseViewModel<SettingsState, UiEvent, SettingsActions> () {
    override fun createInitialState(): SettingsState = SettingsState()

    init {
        viewModelScope.launch{
            dataStoreManager.appState.collect {
                updateState {
                    copy(
                        theme = it.theme,
                        city = it.city,
                        language = it.language
                    )
                }
            }
        }
    }

    override fun applyAction(action: SettingsActions) {
        when(action){
            is SettingsActions.udpateCity -> viewModelScope.launch{
                dataStoreManager.updateCity(action.city)
            }
            is SettingsActions.updateLanguage -> viewModelScope.launch{
                dataStoreManager.updateLanguage(action.language)
            }
            is SettingsActions.updateTheme -> viewModelScope.launch{
                dataStoreManager.updateTheme(action.theme)
            }
        }
    }
}