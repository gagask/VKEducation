package com.example.vkeducation.app_list

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.vkeducation.R
import com.example.vkeducation.data.AppCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppListViewModel: ViewModel() {

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    init {
        loadScreen()
    }

    fun loadScreen() {
        _state.value = AppListState.Loading

        runCatching {
            val appCards = getAppCards()
            _state.value = AppListState.Content(appCards)
        }.onFailure {
            _state.value = AppListState.Error
        }
    }

    private fun getAppCards() = listOf(
        AppCard(
            R.drawable.sber,
            "СберБанк Онлайн - с Салютом",
            "Больше чем банк",
            "Финансы"),
        AppCard(
            R.drawable.yandex_browser,
            "Яндекс.Браузер - с Алисой",
            "Быстрый и безопасный браузер",
            "Инструменты"
        ),
        AppCard(
            R.drawable.mail,
            "Почта Mail.ru",
            "Почтовый клиент для любых ящиков",
            "Инструменты"
        ),
        AppCard(
            R.drawable.yan_nav,
            "Яндекс Навигатор",
            "Парковки и заправки - по пути",
            "Транспорт"
        ),
        AppCard(
            R.drawable.my_mts,
            "Мой МТС",
            "Мой МТС - центр экосистемы МТС",
            "Инструменты"),
        AppCard(
            R.drawable.yan_alisa,
            "Яндекс - с Алисой",
            "Яндекс - поиск всегда под рукой",
            "Инструменты"
        )
    )
}