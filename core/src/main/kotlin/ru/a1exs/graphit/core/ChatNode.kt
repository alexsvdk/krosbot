package ru.a1exs.graphit.core

/**
 * ChatNode
 * Reacts to ChatUpdate
 */
abstract class ChatNode<U : ChatUpdate, Bot>{

    /**
     * Unique chat node id
     */
    open val id = this::class.java.name

    abstract fun receiveUpdate(chatUpdate: U, bot: Bot)
}