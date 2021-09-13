package com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity

import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem

class AddPairUi : ListItem {
    override val viewType: Int = this::class.hashCode()
    override val listId: String = System.currentTimeMillis().toString()
}
