package com.github.alexsvdk.graphit.core.bot

interface ChatUpdateCreator {

    fun addListener(listener: ChatUpdateListener): Boolean
    fun removeListener(listener: ChatUpdateListener): Boolean

}