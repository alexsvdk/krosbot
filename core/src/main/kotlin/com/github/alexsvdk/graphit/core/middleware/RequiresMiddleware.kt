package com.github.alexsvdk.graphit.core.middleware

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
annotation class RequiresMiddleware(
    val kClass: KClass<NodeMiddleware>
)