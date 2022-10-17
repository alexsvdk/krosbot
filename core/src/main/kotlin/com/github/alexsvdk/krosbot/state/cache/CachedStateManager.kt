package com.github.alexsvdk.krosbot.state.cache

import com.github.alexsvdk.krosbot.state.ChatState
import com.github.alexsvdk.krosbot.state.StateManager

open class CachedStateManager<T>(
    private val sourceManager: StateManager<T>,
    private val cacheManager: StateManager<T> = LocalCacheManager(maxSize = 100),
    private val lazySave: Boolean = false
) : StateManager<T> {

    init {
        if (cacheManager is TemporaryStateManager<T>)
            cacheManager.setOnRemoveCallback(::saveToSource)
    }

    override fun get(id: String): T? = cacheManager[id] ?: sourceManager[id]?.also { cacheManager[id] = it }

    override fun set(id: String, data: T) {
        cacheManager[id] = data
        if (!lazySave)
            sourceManager[id] = data
    }

    protected fun saveToSource(chatState: ChatState<T>) {}

}