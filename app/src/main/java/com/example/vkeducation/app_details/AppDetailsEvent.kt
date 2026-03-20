package com.example.vkeducation.app_details

sealed interface AppDetailsEvent {
    data object UnderDevelopment : AppDetailsEvent
}