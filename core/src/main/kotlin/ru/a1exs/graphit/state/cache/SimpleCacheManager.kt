package ru.a1exs.graphit.state.cache

import ru.a1exs.graphit.state.Id
import ru.a1exs.graphit.state.StateManager

class SimpleCacheManager<T : Id>(val maxSize: Int) : StateManager<T> {

    private val map: LinkedHashMap<String, T>

    init {
        assert(maxSize>0)
        map = LinkedHashMap(maxSize)
    }

    override val size
        get() = map.size.toLong()

    override fun get(id: String): T? = map[id]

    override fun set(id: String, data: T) {
        if (map.size == maxSize)
            map.remove(map.entries.first().key)
        map[id] = data
    }

}