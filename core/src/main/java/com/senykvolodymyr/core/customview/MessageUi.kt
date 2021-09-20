package com.senykvolodymyr.core.customview

import com.senykvolodymyr.core.recyclerview.listitem.ListItem

class MessageUi(
    val message: String,
    val messageType: MessageType
) : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = System.currentTimeMillis().toString()
}

enum class MessageType { ERROR, WARNING }
