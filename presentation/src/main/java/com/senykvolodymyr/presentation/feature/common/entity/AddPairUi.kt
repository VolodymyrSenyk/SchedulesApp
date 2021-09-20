package com.senykvolodymyr.presentation.feature.common.entity

import com.senykvolodymyr.core.recyclerview.listitem.ListItem

class AddPairUi : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = System.currentTimeMillis().toString()
}
