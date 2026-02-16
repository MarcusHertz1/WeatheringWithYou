package ru.netology.weatheringwithyou.ui.appBase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface StateViewModel<State : UiState, Event: UiEvent> {
    val state: StateFlow<State>
    val event: Flow<Event>
}