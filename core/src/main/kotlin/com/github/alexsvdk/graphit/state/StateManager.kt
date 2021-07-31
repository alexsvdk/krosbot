package com.github.alexsvdk.graphit.state

import com.github.alexsvdk.graphit.core.ChatUpdate

interface StateManager<T: Id> {

    operator fun get(id: String): T?

    operator fun get(update: ChatUpdate) = get(update.chatId)

    operator fun set(id: String, data: T)

    fun save (data: T) = set(data.id, data)

}