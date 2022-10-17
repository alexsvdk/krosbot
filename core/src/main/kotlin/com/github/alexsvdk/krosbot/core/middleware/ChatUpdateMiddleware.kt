package com.github.alexsvdk.krosbot.core.middleware

import com.github.alexsvdk.krosbot.core.UpdateProcessor

sealed interface ChatUpdateMiddleware : UpdateProcessor

interface GlobalMiddleware : ChatUpdateMiddleware