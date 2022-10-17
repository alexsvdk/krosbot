package com.github.alexsvdk.krosbot.state.cache

import com.github.alexsvdk.krosbot.state.ChatState
import com.github.alexsvdk.krosbot.state.StateManager

abstract class TemporaryStateManager<T>: StateManager<T> {

    protected var onRemoveFromCache: ((ChatState<T>) -> Unit)? = null

    fun setOnRemoveCallback(callback: (ChatState<T>) -> Unit) {
        onRemoveFromCache = callback
    }

}