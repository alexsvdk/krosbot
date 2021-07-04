package ru.a1exs.graphit.state

interface StateManager<T: Id> {

    val size: Long

    operator fun get(id: String): T?

    operator fun set(id: String, data: T)

    fun save (data: T) = set(data.id, data)

}