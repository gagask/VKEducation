package com.example.vkeducation.app_list

import androidx.compose.runtime.Immutable
import com.example.vkeducation.data.AppCard

sealed interface AppListState {
    data object Loading: AppListState
    data object Error: AppListState
    data class Content(val apps: List<AppCard>): AppListState
}