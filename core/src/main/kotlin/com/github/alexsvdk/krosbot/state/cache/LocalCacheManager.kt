package com.github.alexsvdk.krosbot.state.cache

import com.github.alexsvdk.krosbot.state.ChatState

open class LocalCacheManager<T>(
    protected val maxSize: Int = 0,
) : TemporaryStateManager<T>() {

    protected val map: LinkedHashMap<String, T> = if (maxSize > 0) LinkedHashMap(maxSize) else LinkedHashMap()

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            map.forEach { key, value -> onRemoveFromCache?.invoke(ChatState(key, value)) }
        })
    }

    val size
        get() = map.size.toLong()

    override fun get(id: String): T? = map[id]

    override fun set(id: String, data: T) {
        if (maxSize > 0 && map.size == maxSize) {
            val removedKey = map.entries.first().key
            map.remove(removedKey)?.let { removedValue ->
                onRemoveFromCache?.invoke(ChatState(removedKey, removedValue))
            }
        }
        map[id] = data
    }

}