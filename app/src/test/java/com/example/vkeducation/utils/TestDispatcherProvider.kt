package com.example.vkeducation.utils

import com.example.vkeducation.core.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatcherProvider(
    private val dispatcher: CoroutineDispatcher
) : DispatcherProvider {
    override val io = dispatcher
    override val main = dispatcher
    override val default = dispatcher
}