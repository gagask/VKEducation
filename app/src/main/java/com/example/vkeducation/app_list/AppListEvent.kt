package com.example.vkeducation.app_list

sealed interface AppListEvent {
    data object RuStoreLogoClicked: AppListEvent
}