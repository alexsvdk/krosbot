package ru.a1exs.graphit.core

interface ChatUpdateCreator<U : ChatUpdate, Bot> {

    fun addListener(listener: ChatUpdateReceiver<U,Bot>): Boolean
    fun removeListener(listener: ChatUpdateReceiver<U,Bot>): Boolean

}

