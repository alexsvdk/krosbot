package com.github.alexsvdk.graphit.state.cache

import com.github.alexsvdk.graphit.state.Id
import com.github.alexsvdk.graphit.state.StateManager

class LocalCacheManager<T : Id>(val maxSize: Int) : StateManager<T> {

    private val map: LinkedHashMap<String, T>

    init {
        assert(maxSize > 0)
        map = LinkedHashMap(maxSize)
    }

    val size
        get() = map.size.toLong()

    override fun get(id: String): T? = map[id]

    override fun set(id: String, data: T) {
        if (map.size == maxSize)
            map.remove(map.entries.first().key)
        map[id] = data
    }

}