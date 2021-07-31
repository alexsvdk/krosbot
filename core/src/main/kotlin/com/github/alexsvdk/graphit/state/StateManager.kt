package com.github.alexsvdk.graphit.state

interface StateManager<T: Id> {

    operator fun get(id: String): T?

    operator fun set(id: String, data: T)

    fun save (data: T) = set(data.id, data)

}