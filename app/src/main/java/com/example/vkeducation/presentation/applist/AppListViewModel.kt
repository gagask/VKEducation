package com.example.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.data.AppCardMapper
import com.example.vkeducation.data.AppListApi
import com.example.vkeducation.data.AppListMockRepositoryImpl
import com.example.vkeducation.data.AppTypeMapper
import com.example.vkeducation.domain.AppListRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel: ViewModel() {

    private val appListRepository: AppListRepository = AppListMockRepositoryImpl(
        mapper = AppCardMapper(AppTypeMapper()),
        api = AppListApi()
    )

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppListEvent>(BUFFERED)
    val events = _events.receiveAsFlow()


    init {
        loadScreen()
    }

    fun showLogoMessage() {
        viewModelScope.launch {
            _events.send(AppListEvent.RuStoreLogoClicked)
        }
    }

    fun loadScreen() {
        viewModelScope.launch {
            _state.value = AppListState.Loading

            runCatching {
                val appCards = appListRepository.get()
                _state.value = AppListState.Content(appCards)
            }.onFailure { error ->
                _state.value = AppListState.Error
                if (error is CancellationException)
                    throw error
            }
        }

    }
}