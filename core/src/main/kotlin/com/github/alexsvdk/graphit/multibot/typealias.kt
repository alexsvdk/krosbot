package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.state.UpdateStateMapper
import com.github.alexsvdk.graphit.state.StatefulNode

typealias MultiBotNode = ChatNode<MultiBotChatUpdate, MultiBotSender>
interface MultiBotStatefulNode<State>: StatefulNode<MultiBotChatUpdate, MultiBotSender, State>
interface MultiBotStateMapper<State>: UpdateStateMapper<MultiBotChatUpdate, State>