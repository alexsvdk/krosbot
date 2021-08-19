package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.ChatUpdateCreator
import com.github.alexsvdk.graphit.nodes.graphit.GraphitNode

typealias MultiBotNode = ChatNode<MultiBotChatUpdate, MultiBotSender>
typealias MultiBotGraphitNode = GraphitNode<MultiBotChatUpdate, MultiBotSender>
typealias MultiBotUpdateCreator = ChatUpdateCreator<MultiBotChatUpdate, MultiBotSender>