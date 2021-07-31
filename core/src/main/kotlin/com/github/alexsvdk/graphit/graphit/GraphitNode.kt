package com.github.alexsvdk.graphit.graphit

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.ChatUpdate
import com.github.alexsvdk.graphit.state.StateManager

open class GraphitNode<U : ChatUpdate, Bot>(
    protected val stateManager: StateManager<GraphitNodeState>,
) : ChatNode<U, Bot> {

    protected val nodes = mutableMapOf<String, ChatNode<U, Bot>>()

    open val initialNode: ChatNode<U, Bot>
        get() = nodes.values.first()

    open fun registerNodes(vararg chatNode: ChatNode<U, Bot>) = chatNode.forEach {
        nodes[it.id] = it
    }

    override fun receiveUpdate(chatUpdate: U, bot: Bot) {
        val nodeState = stateManager[chatUpdate] ?: GraphitNodeState(chatUpdate.chatId, initialNode.id)
        nodes[nodeState.id]?.receiveUpdate(chatUpdate, bot)
    }
}