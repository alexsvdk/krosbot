package ru.a1exs.graphit.state.cache

import ru.a1exs.graphit.state.Id
import ru.a1exs.graphit.state.StateManager

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