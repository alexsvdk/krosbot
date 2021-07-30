package com.github.alexsvdk.graphit.state

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.ChatUpdate

interface StatefulNode<U : ChatUpdate, Bot, State> : ChatNode<U, Bot> {

    val updateStateMapper: UpdateStateMapper<U, State>

    override fun receiveUpdate(chatUpdate: U, bot: Bot) =
        receiveUpdate(chatUpdate, bot, updateStateMapper.mapUpdateToState(chatUpdate))

    fun receiveUpdate(chatUpdate: U, bot: Bot, state: State)

}