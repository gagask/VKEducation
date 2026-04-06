package com.example.vkeducation.app_list

sealed interface AppListState {
    data object Loading: AppListState
    data object Error: AppListState
    data class Content(val apps: List<AppCardInfo>): AppListState
}