package com.github.alexsvdk.graphit.core.bot

import com.github.alexsvdk.graphit.core.sender.MessageSender

interface ChatUpdateListener {
    fun receiveUpdate(update: ChatUpdate, sender: MessageSender)
}