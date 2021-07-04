package ru.a1exs.graphit.core

/**
 * ChatNode receives and reacts to any chat update
 */

abstract class ChatNode<U : ChatUpdate, G: Graphit<U>>{

    /**
     * Unique chat node id
     */
    open val id = this::class.java.name

    abstract fun receiveUpdate(chatUpdate: U, graphit: G)
}