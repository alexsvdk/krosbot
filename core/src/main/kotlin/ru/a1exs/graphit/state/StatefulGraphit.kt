package ru.a1exs.graphit.state

import ru.a1exs.graphit.core.ChatUpdate
import ru.a1exs.graphit.core.Graphit

abstract class StatefulGraphit<U: ChatUpdate>: Graphit<U>() {

}