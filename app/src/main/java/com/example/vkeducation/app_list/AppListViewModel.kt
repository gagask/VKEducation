package com.example.vkeducation.app_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel: ViewModel() {

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
        _state.value = AppListState.Loading

        runCatching {
            val appCards = getAppCards()
            _state.value = AppListState.Content(appCards)
        }.onFailure { error ->
            _state.value = AppListState.Error
            if (error is CancellationException)
                throw error
        }
    }

    private fun getAppCards() = listOf(
        AppCardInfo(
            R.drawable.sber,
            "СберБанк Онлайн - с Салютом",
            "Больше чем банк",
            "Финансы"),
        AppCardInfo(
            R.drawable.yandex_browser,
            "Яндекс.Браузер - с Алисой",
            "Быстрый и безопасный браузер",
            "Инструменты"
        ),
        AppCardInfo(
            R.drawable.mail,
            "Почта Mail.ru",
            "Почтовый клиент для любых ящиков",
            "Инструменты"
        ),
        AppCardInfo(
            R.drawable.yan_nav,
            "Яндекс Навигатор",
            "Парковки и заправки - по пути",
            "Транспорт"
        ),
        AppCardInfo(
            R.drawable.my_mts,
            "Мой МТС",
            "Мой МТС - центр экосистемы МТС",
            "Инструменты"),
        AppCardInfo(
            R.drawable.yan_alisa,
            "Яндекс - с Алисой",
            "Яндекс - поиск всегда под рукой",
            "Инструменты"
        )
    )
}