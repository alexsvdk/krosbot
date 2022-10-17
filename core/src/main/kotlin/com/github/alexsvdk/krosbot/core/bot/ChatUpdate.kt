package com.github.alexsvdk.krosbot.core.bot

import com.github.alexsvdk.krosbot.core.ChatInfo
import com.github.alexsvdk.krosbot.core.UserInfo
import com.github.alexsvdk.krosbot.core.message.IncomingMessageComponent

/**
 * Any chat update that receives Graphit from Bot
 * It can be a Message, Callback, or something else
 */
interface ChatUpdate{

    val chatInfo: ChatInfo
    val from: UserInfo

    val messageId: String?
    val incomingComponents: List<IncomingMessageComponent>

}