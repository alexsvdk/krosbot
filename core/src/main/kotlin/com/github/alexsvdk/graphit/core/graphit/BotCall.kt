package com.github.alexsvdk.graphit.core.graphit

import com.github.alexsvdk.graphit.core.bot.ChatUpdate
import com.github.alexsvdk.graphit.core.message.OutgoingMessageComponent
import com.github.alexsvdk.graphit.core.middleware.ChatUpdateMiddleware
import com.github.alexsvdk.graphit.core.sender.MessageSender
import com.github.alexsvdk.graphit.core.sender.SenderCall
import com.github.alexsvdk.graphit.state.StateManager
import kotlin.reflect.KClass

typealias StateGetter = () -> BotCall.State

private val <T : ChatUpdateMiddleware> KClass<T>.id get() = java.name

open class BotCall(
    val update: ChatUpdate,
    protected val sender: MessageSender,
    private val stateGetter: StateGetter
) : MessageSender {

    enum class State {
        /// Бот создал BotCall, никто его не обрабатывал
        CREATED,

        /// BotCall обрабатывается всякими middleware
        MIDDLEWARE,

        /// BotCall обрабатывается нодой
        CHAT_NODE,

        /// BotCall обработан
        POST_CALLBACK,
    }

    val state get() = stateGetter()
    val incomingComponents get() = update.incomingComponents

    protected val mutableSenderCalls = mutableListOf<SenderCall>()
    val senderCalls: List<SenderCall>
        get() = mutableSenderCalls

    protected val mutableMiddlewarePayload = mutableMapOf<String, Any?>()
    open fun <T : ChatUpdateMiddleware> middlewarePayload(kClass: KClass<T>): Any? = mutableMiddlewarePayload[kClass.id]
    inline fun <reified T : ChatUpdateMiddleware> middlewarePayload(): Any? = middlewarePayload(T::class)

    open fun <T : ChatUpdateMiddleware> addMiddlewarePayload(kClass: KClass<T>, payload: Any?) {
        assert(state in listOf(State.MIDDLEWARE)) {
            "You can add middleware payloads only from ChatUpdateMiddleware"
        }
        mutableMiddlewarePayload[kClass.id] = payload
    }

    fun <T> getState(stateManager: StateManager<T>) = stateManager[update.chatInfo.id]

    inline fun <reified T : ChatUpdateMiddleware> addMiddlewarePayload(payload: Any?) =
        addMiddlewarePayload(T::class, payload)

    open fun sendMessage(message: List<OutgoingMessageComponent>) =
        sendMessage(update.chatInfo.id, message)

    open fun updateMessage(messageId: String, message: List<OutgoingMessageComponent>) =
        updateMessage(update.chatInfo.id, messageId, message)

    open fun removeMessage(messageId: String) =
        removeMessage(update.chatInfo.id, messageId)

    override fun executeCall(senderCall: SenderCall) {
        assert(state in listOf(State.MIDDLEWARE, State.CHAT_NODE)) {
            "You can send messages only from Middleware and ChatNode"
        }
        try {
            sender.executeCall(senderCall)
        } catch (e: Exception) {
            senderCall.result = SenderCall.Result(senderCall.result?.messageIds, e)
        }
        mutableSenderCalls.add(senderCall)
    }

}