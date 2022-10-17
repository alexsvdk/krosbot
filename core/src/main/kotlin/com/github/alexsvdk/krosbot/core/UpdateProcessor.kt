package com.github.alexsvdk.krosbot.core

import com.github.alexsvdk.krosbot.core.graphit.BotCall

interface UpdateProcessor {
    fun BotCall.processUpdate()
}