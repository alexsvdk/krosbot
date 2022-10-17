package com.github.alexsvdk.krosbot.core.bot

import com.github.alexsvdk.krosbot.core.sender.MessageSender

interface ChatUpdateListener {
    fun receiveUpdate(update: ChatUpdate, sender: MessageSender)
}