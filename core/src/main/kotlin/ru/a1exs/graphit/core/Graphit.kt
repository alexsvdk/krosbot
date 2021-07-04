package ru.a1exs.graphit.core

abstract class Graphit<U : ChatUpdate> {
    private val nodes = mutableMapOf<String, ChatNode<U, Graphit<U>>>()

    /**
     * Registers [node] in Graphit
     */
    protected fun registerNode(node: ChatNode<U, Graphit<U>>) {
        nodes[node.id] = node
    }

    /**
     * Finds node in Graphit by [id]
     */
    protected fun getNodeById(id: String): ChatNode<U, Graphit<U>>? = nodes[id]

    abstract fun getNodeForUpdate(chatUpdate: U): ChatNode<U, Graphit<U>>

    open fun handleUpdate(chatUpdate: U) = getNodeForUpdate(chatUpdate).receiveUpdate(chatUpdate, this)
}