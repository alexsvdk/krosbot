package com.github.alexsvdk.graphit.core.message.utils

import com.github.alexsvdk.graphit.core.message.abstract.KeyboardMessageComponent

class PaginationKeyboardBuilder(initializer: (PaginationKeyboardBuilder.() -> Unit)? = null) {

    init {
        initializer?.let { this.it() }
    }

    var keyboardItems: List<String>? = null
    var keyboardItemBuilder: ((index: Int) -> String)? = null
    var maxRows: Int = 2
    var maxColumns: Int = 8
    val totalItems: Int? = null
    val pageNumber: Int = 0

    val nextPageButtonBuilder: ((pageIndex: Int) -> String) = {">"}
    val prevPageButtonBuilder: ((pageIndex: Int) -> String) = {"<"}

    val maxItems get() = maxRows * maxColumns

    fun build(): KeyboardMessageComponent {
        assert(totalItems != null) { "totalItems must not be null" }
        assert(totalItems!! > 2) { "totalItems must not be > 2" }
        assert(pageNumber > 0)
        assert(maxRows > 0)
        assert(maxColumns > 0)
        assert((keyboardItems != null) xor (keyboardItemBuilder != null)) { "Only one of keyboardItems and keyboardItemBuilder should be passed" }

        val prevItems = if (pageNumber==0) 0 else pageNumber * (maxItems-1) - (pageNumber-1)
        assert(prevItems < totalItems)

        val showPrev = pageNumber>0

        TODO()
    }

}