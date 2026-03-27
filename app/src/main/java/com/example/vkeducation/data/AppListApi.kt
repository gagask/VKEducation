package com.example.vkeducation.data

import com.example.vkeducation.R
import com.example.vkeducation.data.dto.AppCardDto
import jakarta.inject.Inject

class AppListApi @Inject constructor() {
    suspend fun get(): List<AppCardDto> {
        return listOf(
            AppCardDto(
                R.drawable.sber,
                "СберБанк Онлайн - с Салютом",
                "Больше чем банк",
                "Finance"
            ),
            AppCardDto(
                R.drawable.yandex_browser,
                "Яндекс.Браузер - с Алисой",
                "Быстрый и безопасный браузер",
                "Tools"
            ),
            AppCardDto(
                R.drawable.mail,
                "Почта Mail.ru",
                "Почтовый клиент для любых ящиков",
                "Tools"
            ),
            AppCardDto(
                R.drawable.yan_nav,
                "Яндекс Навигатор",
                "Парковки и заправки - по пути",
                "Transport"
            ),
            AppCardDto(
                R.drawable.my_mts,
                "Мой МТС",
                "Мой МТС - центр экосистемы МТС",
                "Tools"
            ),
            AppCardDto(
                R.drawable.yan_alisa,
                "Яндекс - с Алисой",
                "Яндекс - поиск всегда под рукой",
                "Tools"
            )
        )
    }
}