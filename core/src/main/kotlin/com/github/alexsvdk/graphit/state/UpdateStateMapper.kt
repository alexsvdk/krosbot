package com.github.alexsvdk.graphit.state

import com.github.alexsvdk.graphit.core.ChatUpdate

interface UpdateStateMapper<U: ChatUpdate, State> {
    fun mapUpdateToState(update: U): State
}