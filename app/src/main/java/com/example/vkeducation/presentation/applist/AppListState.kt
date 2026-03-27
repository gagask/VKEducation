package com.example.vkeducation.presentation.applist

import com.example.vkeducation.domain.App

sealed interface AppListState {
    data object Loading: AppListState
    data object Error: AppListState
    data class Content(val apps: List<App>): AppListState
}