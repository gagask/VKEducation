package com.example.vkeducation.presentation.appdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.vkeducation.domain.AppRepository
import com.example.vkeducation.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route = savedStateHandle.toRoute<Screen.Details>()
    private val id = route.id

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        getAppDetails()
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }

    fun getAppDetails() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading

            runCatching {
                val appDetails = repository.getAppDetails(id)

                _state.value = AppDetailsState.Content(
                    appDetails = appDetails,
                    descriptionCollapsed = false,
                )
            }.onFailure { error ->
                Log.d("AppDetailsVM", "${error.message}")
                _state.value = AppDetailsState.Error
            }
        }
    }
}