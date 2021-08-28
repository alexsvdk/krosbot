package com.github.alexsvdk.graphit.state

import com.github.alexsvdk.graphit.core.bot.ChatUpdate

interface StateManager<T> {

    operator fun get(id: String): T?

    operator fun get(update: ChatUpdate) = get(update.chatInfo.id)

    operator fun set(id: String, data: T)

    fun save (state: ChatState<T>) = set(state.chatId, state.data)

}