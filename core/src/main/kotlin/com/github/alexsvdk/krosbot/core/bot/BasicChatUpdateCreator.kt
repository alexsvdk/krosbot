package com.github.alexsvdk.krosbot.core.bot

import com.github.alexsvdk.krosbot.core.sender.MessageSender

open class BasicChatUpdateCreator : ChatUpdateCreator {

    protected val updateListeners = mutableSetOf<ChatUpdateListener>()

    override fun addListener(listener: ChatUpdateListener): Boolean =
        updateListeners.add(listener)

    override fun removeListener(listener: ChatUpdateListener): Boolean =
        updateListeners.remove(listener)

    fun emitUpdate(chatUpdate: ChatUpdate, messageSender: MessageSender) = updateListeners.forEach {
        it.receiveUpdate(chatUpdate, messageSender)
    }

}