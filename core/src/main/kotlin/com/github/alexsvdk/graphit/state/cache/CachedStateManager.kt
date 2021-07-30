package com.github.alexsvdk.graphit.state.cache

import com.github.alexsvdk.graphit.state.Id
import com.github.alexsvdk.graphit.state.StateManager

class CachedStateManager<T : Id>(
        private val stateManager: StateManager<T>,
        private val cacheManager: StateManager<T>
) : StateManager<T> {

    override val size
        get() = stateManager.size

    override fun get(id: String): T? = cacheManager[id] ?: stateManager[id]?.also { cacheManager.save(it) }

    override fun set(id: String, data: T) {
        cacheManager[id] = data
        stateManager[id] = data
    }

}