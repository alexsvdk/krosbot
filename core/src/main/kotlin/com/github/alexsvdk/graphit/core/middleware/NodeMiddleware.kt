package com.github.alexsvdk.graphit.core.middleware

import com.github.alexsvdk.graphit.core.ChatNode
import kotlin.reflect.KClass

abstract class NodeMiddleware : ChatUpdateMiddleware{

    fun <T: ChatNode> shouldProcess(nodeClass: KClass<T>) = nodeClass.annotations.any {
        if (it !is RequiresMiddleware) false
        (it as RequiresMiddleware).let {
            it.kClass == this::class
        }
    }

}