package com.github.alexsvdk.graphit.core.graphit

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.bot.ChatUpdate
import com.github.alexsvdk.graphit.core.bot.ChatUpdateListener
import com.github.alexsvdk.graphit.core.middleware.ChatUpdateMiddleware
import com.github.alexsvdk.graphit.core.middleware.GlobalMiddleware
import com.github.alexsvdk.graphit.core.middleware.NodeMiddleware
import com.github.alexsvdk.graphit.core.middleware.PostCallCallback
import com.github.alexsvdk.graphit.core.sender.MessageSender
import com.github.alexsvdk.graphit.state.StateManager
import com.github.alexsvdk.graphit.state.cache.LocalCacheManager

open class Graphit(
    val graphitStateManager: StateManager<String> = LocalCacheManager()
) : ChatUpdateListener {

    protected val nodes = mutableMapOf<String, ChatNode>()
    protected val middlewares = mutableSetOf<ChatUpdateMiddleware>()
    protected val postCallbacks = mutableSetOf<PostCallCallback>()

    var defaultNode: ChatNode? = null
        set(value) {
            assert(value != null) { "Default node must not be null" }
            field = value
        }

    fun registerNode(node: ChatNode) {
        if (defaultNode == null) defaultNode = node
        nodes[node.id] = node
    }

    fun registerMiddleware(middleware: ChatUpdateMiddleware) {
        middlewares.add(middleware)
    }

    fun registerPostCallback(postCallCallback: PostCallCallback) {
        postCallbacks.add(postCallCallback)
    }

    protected fun findNodeForUpdate(update: ChatUpdate): ChatNode {
        assert(defaultNode != null) { "Default node must not be null" }
        return graphitStateManager[update.chatInfo.id]?.let {
            nodes[it]
        } ?: defaultNode!!
    }

    override fun receiveUpdate(update: ChatUpdate, sender: MessageSender) {
        var callState = BotCall.State.CREATED
        val node = findNodeForUpdate(update)
        val botCall = BotCall(
            update,
            sender
        ) { callState }
        callState = BotCall.State.MIDDLEWARE
        middlewares.filter {
            when (it) {
                is GlobalMiddleware -> true
                is NodeMiddleware -> it.shouldProcess(node::class)
            }
        }.forEach { with(it) { botCall.processUpdate() } }
        callState = BotCall.State.CHAT_NODE
        with(node) { botCall.processUpdate() }
        callState = BotCall.State.POST_CALLBACK
        postCallbacks.forEach { with(it) { botCall.processUpdate() } }
    }
}