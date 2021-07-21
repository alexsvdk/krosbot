package ru.a1exs.graphit.state

import ru.a1exs.graphit.core.ChatUpdate

interface StateMapper<U: ChatUpdate, State> {
    fun mapUpdateToState(update: U): State
}