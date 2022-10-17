package com.github.alexsvdk.krosbot.core

/**
 * ChatNode
 * Reacts to ChatUpdate
 */
interface ChatNode: UpdateProcessor {

    /**
     * Unique chat node id
     */
    val id
        get() = this::class.java.name

}