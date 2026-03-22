package com.example.vkeducation.presentation.applist

sealed interface AppListEvent {
    data object RuStoreLogo: AppListEvent
}