package com.senyk.volodymyr.schedulesapp.presentation.core.customview

import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem

class MessageUi(
    val message: String,
    val messageType: MessageType
) : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = System.currentTimeMillis().toString()
}

enum class MessageType { ERROR, WARNING }
