package com.github.alexsvdk.krosbot.core.message.utils

import com.github.alexsvdk.krosbot.core.message.KeyboardMessageComponent
import com.github.alexsvdk.krosbot.core.message.OutgoingKeyboard

class PaginationKeyboardBuilder(initializer: (PaginationKeyboardBuilder.() -> Unit)? = null) {

    init {
        initializer?.let { this.it() }
    }

    var keyboardType = KeyboardMessageComponent.Type.KEYBOARD
    var keyboardItems: List<String>? = null
    var keyboardItemBuilder: ((index: Int) -> KeyboardMessageComponent.Button)? = null
    var maxRows: Int = 2
    var maxColumns: Int = 8
    var totalItems: Int? = null
    var pageNumber: Int = 0

    var nextPageButtonBuilder: ((pageIndex: Int) -> KeyboardMessageComponent.Button) =
        { KeyboardMessageComponent.Button(">") }
    var prevPageButtonBuilder: ((pageIndex: Int) -> KeyboardMessageComponent.Button) =
        { KeyboardMessageComponent.Button("<") }

    val maxItems get() = maxRows * maxColumns

    fun build(): OutgoingKeyboard {
        assert(totalItems != null) { "totalItems must not be null" }
        assert(totalItems!! > 2) { "totalItems must not be > 2" }
        assert(pageNumber > 0)
        assert(maxRows > 0)
        assert(maxColumns > 0)
        assert((keyboardItems != null) xor (keyboardItemBuilder != null)) { "Only one of keyboardItems and keyboardItemBuilder should be passed" }

        var itemsForThisPage = maxItems
        val prevItems = if (pageNumber == 0) 0 else pageNumber * (maxItems - 2) + 1
        assert(prevItems < totalItems!!)

        val showPrev = prevItems > 0
        if (showPrev) itemsForThisPage--

        val showNext = totalItems!! - prevItems - itemsForThisPage > 0
        if (showNext) itemsForThisPage--

        return OutgoingKeyboard(
            List(maxRows) { row ->
                List(maxColumns) { col ->
                    val index = row * maxColumns + col
                    if (index == 0 && showPrev) prevPageButtonBuilder(pageNumber - 1)
                    if (index == maxItems - 1 && showNext) nextPageButtonBuilder(pageNumber + 1)
                    keyboardItemBuilder!!.invoke(prevItems + index)
                }
            },
            keyboardType
        )
    }

}