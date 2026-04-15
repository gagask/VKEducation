package com.example.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppListEvent>(BUFFERED)
    val events = _events.receiveAsFlow()


    init {
        loadScreen()
    }

    fun showLogoMessage() {
        viewModelScope.launch {
            _events.send(AppListEvent.RuStoreLogo)
        }
    }

    fun loadScreen() {
        viewModelScope.launch {
            _state.value = AppListState.Loading

            runCatching {
                val appCards = appRepository.getApps()
                _state.value = AppListState.Content(appCards)
            }.onFailure { error ->
                Timber.d("${error.message}")
                _state.value = AppListState.Error
            }
        }

    }
}