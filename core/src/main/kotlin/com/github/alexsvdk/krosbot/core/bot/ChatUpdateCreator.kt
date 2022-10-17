package com.github.alexsvdk.krosbot.core.bot

interface ChatUpdateCreator {

    fun addListener(listener: ChatUpdateListener): Boolean
    fun removeListener(listener: ChatUpdateListener): Boolean

}