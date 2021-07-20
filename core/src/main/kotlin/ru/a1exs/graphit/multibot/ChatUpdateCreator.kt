package ru.a1exs.graphit.multibot

typealias UpdateListener = (MultiBotChatUpdate) -> Unit

interface ChatUpdateCreator {

    var updateListener: UpdateListener?

}