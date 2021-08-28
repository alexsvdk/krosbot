package com.github.alexsvdk.graphit.state.cache

import com.github.alexsvdk.graphit.state.ChatState
import com.github.alexsvdk.graphit.state.StateManager

abstract class TemporaryStateManager<T>: StateManager<T> {

    protected var onRemoveFromCache: ((ChatState<T>) -> Unit)? = null

    fun setOnRemoveCallback(callback: (ChatState<T>) -> Unit) {
        onRemoveFromCache = callback
    }

}