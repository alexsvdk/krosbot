package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.multibot.message.MessageComponent

interface MultiBot {

    fun sendMessage(to: String, data: List<MessageComponent>)

}