package com.github.alexsvdk.graphit.core

import com.github.alexsvdk.graphit.core.graphit.BotCall

interface UpdateProcessor {
    fun BotCall.processUpdate()
}