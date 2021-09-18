package com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem

data class EmptyStateListItem(
    val message: String? = null
) : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = this::class.hashCode().toString()
}
