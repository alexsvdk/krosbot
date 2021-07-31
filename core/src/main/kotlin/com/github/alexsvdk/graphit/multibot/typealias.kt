package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.ChatUpdateCreator

typealias MultiBotNode = ChatNode<MultiBotChatUpdate, MultiBotSender>
typealias MultiBotUpdateCreator = ChatUpdateCreator<MultiBotChatUpdate, MultiBotSender>