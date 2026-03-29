package com.example.vkeducation.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object List: Screen
    @Serializable data class Details(val id: String): Screen
}