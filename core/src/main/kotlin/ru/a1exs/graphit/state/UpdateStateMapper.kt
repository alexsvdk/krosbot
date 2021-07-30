package ru.a1exs.graphit.state

import ru.a1exs.graphit.core.ChatUpdate

interface UpdateStateMapper<U: ChatUpdate, State> {
    fun mapUpdateToState(update: U): State
}