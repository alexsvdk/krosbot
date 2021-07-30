package com.github.alexsvdk.graphit.core

/**
 * ChatNode
 * Reacts to ChatUpdate
 */
interface ChatNode<U : ChatUpdate, Bot> : ChatUpdateReceiver<U, Bot> {

    /**
     * Unique chat node id
     */
    val id
        get() = this::class.java.name

    fun addUpdateCreator(chatUpdateCreator: ChatUpdateCreator<U, Bot>) = chatUpdateCreator.addListener(this)
    fun removeUpdateCreator(chatUpdateCreator: ChatUpdateCreator<U, Bot>) = chatUpdateCreator.removeListener(this)
}