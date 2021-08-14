package com.github.alexsvdk.graphit.nodes.proxy

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.ChatUpdate

abstract class ProxyNode<U : ChatUpdate, Bot>(
    val node: ChatNode<U, Bot>
) : ChatNode<U, Bot>{

}