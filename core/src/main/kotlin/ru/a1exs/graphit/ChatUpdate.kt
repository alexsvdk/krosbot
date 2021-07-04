package ru.a1exs.graphit

/**
 * Any update in chat
 *
 * @property chatId unique id of chat
 */

abstract class ChatUpdate(open val chatId: String) {

}