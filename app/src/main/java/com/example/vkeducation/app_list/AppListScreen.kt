package com.example.vkeducation.app_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vkeducation.R
import com.example.vkeducation.data.AppCard

@Composable
fun AppListScreen(
    modifier: Modifier = Modifier,
    onAppCardClicked: () -> Unit = {}
) {
    Scaffold(
        containerColor = Color(0xff0077FF),
        modifier = Modifier.fillMaxSize(),
        topBar = { AppListHeader() }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
                .padding(top = 16.dp)
        ) {
            items(getAppCards()){ appCard ->
                AppCard(appCard, onAppCardClicked)
            }
        }
    }
}


private fun getAppCards() = listOf(
    AppCard(R.drawable.sber, "СберБанк Онлайн - с Салютом", "Больше чем банк", "Финансы"),
    AppCard(R.drawable.yandex_browser, "Яндекс.Браузер - с Алисой", "Быстрый и безопасный браузер", "Инструменты"),
    AppCard(R.drawable.mail, "Почта Mail.ru", "Почтовый клиент для любых ящиков", "Инструменты"),
    AppCard(R.drawable.yan_nav, "Яндекс Навигатор", "Парковки и заправки - по пути", "Транспорт"),
    AppCard(R.drawable.my_mts, "Мой МТС", "Мой МТС - центр экосистемы МТС", "Инструменты"),
    AppCard(R.drawable.yan_alisa, "Яндекс - с Алисой", "Яндекс - поиск всегда под рукой", "Инструменты")
)