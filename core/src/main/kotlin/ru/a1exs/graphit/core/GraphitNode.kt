package ru.a1exs.graphit.core

/**
 * Receives all chat updates from bot
 * Chooses the right ChatNode to handle this update
 */
abstract class GraphitNode<U : ChatUpdate, Bot>: ChatNode<U, Bot>() {
    private val nodes = mutableMapOf<String, ChatNode<U, Bot>>()

    /**
     * Registers [node] in Graphit
     */
    protected fun registerNode(node: ChatNode<U, Bot>) {
        nodes[node.id] = node
    }

    /**
     * Finds node in Graphit by [id]
     */
    protected fun getNodeById(id: String): ChatNode<U, Bot>? = nodes[id]

    abstract fun getNodeForUpdate(chatUpdate: U): ChatNode<U, Bot>

    override fun receiveUpdate(chatUpdate: U, bot: Bot) = getNodeForUpdate(chatUpdate).receiveUpdate(chatUpdate, bot)
}