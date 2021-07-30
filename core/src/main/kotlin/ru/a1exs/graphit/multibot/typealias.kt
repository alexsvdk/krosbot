package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.core.ChatNode
import ru.a1exs.graphit.state.UpdateStateMapper
import ru.a1exs.graphit.state.StatefulNode

typealias MultiBotNode = ChatNode<MultiBotChatUpdate, MultiBotSender>
interface MultiBotStatefulNode<State>: StatefulNode<MultiBotChatUpdate, MultiBotSender, State>
interface MultiBotStateMapper<State>: UpdateStateMapper<MultiBotChatUpdate, State>