package ru.a1exs.graphit.core

/**
 * Any chat update that receives Graphit from Bot
 * It can be a Message, Callback, or something else
 *
 * @property chatId unique id of chat
 */
abstract class ChatUpdate(open val chatId: String)