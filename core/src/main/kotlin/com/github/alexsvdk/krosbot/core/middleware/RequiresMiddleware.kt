package com.github.alexsvdk.krosbot.core.middleware

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
annotation class RequiresMiddleware(
    val kClass: KClass<NodeMiddleware>
)