package com.github.alexsvdk.graphit.core.middleware

import com.github.alexsvdk.graphit.core.ChatNode
import com.github.alexsvdk.graphit.core.UpdateProcessor

sealed interface ChatUpdateMiddleware : UpdateProcessor

interface GlobalMiddleware : ChatUpdateMiddleware