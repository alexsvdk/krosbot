package ru.a1exs.graphit.core

class BasicUpdateCreator<U : ChatUpdate, Bot> : ChatUpdateCreator<U, Bot> {

    private val listeners = mutableSetOf<ChatUpdateReceiver<U, Bot>>()

    override fun addListener(listener: ChatUpdateReceiver<U, Bot>) =
        listeners.add(listener)

    override fun removeListener(listener: ChatUpdateReceiver<U, Bot>) =
        listeners.remove(listener)

    fun publishChatUpdate(update: U, bot: Bot) = listeners.forEach { it.receiveUpdate(update, bot) }

}