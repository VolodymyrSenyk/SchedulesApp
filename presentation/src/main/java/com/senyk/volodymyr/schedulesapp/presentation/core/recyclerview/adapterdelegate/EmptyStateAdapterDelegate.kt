package com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.senyk.volodymyr.schedulesapp.databinding.ListItemEmptyStateBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.EmptyStateListItem
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.viewholder.BaseDataBindingViewHolder

class EmptyStateAdapterDelegate : AdapterDelegate<List<ListItem>>() {

    override fun isForViewType(items: List<ListItem>, position: Int): Boolean =
        items[position] is EmptyStateListItem

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemEmptyStateBinding.inflate(inflater, parent, false)
        return BaseDataBindingViewHolder<ListItemEmptyStateBinding>(binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<ListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as? BaseDataBindingViewHolder<ListItemEmptyStateBinding> ?: return
        val model = items[position] as? EmptyStateListItem ?: return
        viewHolder.binding?.model = model
    }
}
