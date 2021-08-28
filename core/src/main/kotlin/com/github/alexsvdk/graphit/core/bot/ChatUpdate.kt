package com.github.alexsvdk.graphit.core.bot

import com.github.alexsvdk.graphit.core.ChatInfo
import com.github.alexsvdk.graphit.core.UserInfo
import com.github.alexsvdk.graphit.core.message.IncomingMessageComponent

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