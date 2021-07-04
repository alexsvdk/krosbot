package ru.a1exs.graphit

abstract class Graphit<U: ChatUpdate> {

    protected val nodes = mutableMapOf<String,ChatNode<U, Graphit<U>>>()

    fun registerNode(node: ChatNode<U, Graphit<U>>){
        nodes[node.id] = node
    }

    abstract fun getNodeForUpdate(chatUpdate: U): ChatNode<U, Graphit<U>>

    open fun handleUpdate(chatUpdate: U) = getNodeForUpdate(chatUpdate).receiveUpdate(chatUpdate,this)

}