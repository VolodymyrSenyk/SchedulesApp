package com.senykvolodymyr.presentation.feature.schedules.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.senykvolodymyr.core.recyclerview.listitem.ListItem
import com.senykvolodymyr.core.recyclerview.viewholder.BaseDataBindingViewHolder
import com.senykvolodymyr.presentation.databinding.ListItemAddPairBinding
import com.senykvolodymyr.presentation.feature.common.entity.AddPairUi
import com.senykvolodymyr.presentation.feature.schedules.OneDayScheduleViewModel

class AddPairAdapterDelegate(
    private val viewModel: OneDayScheduleViewModel
) : AdapterDelegate<List<ListItem>>() {

    override fun isForViewType(items: List<ListItem>, position: Int): Boolean =
        items[position] is AddPairUi

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemAddPairBinding.inflate(inflater, parent, false)
        return BaseDataBindingViewHolder<ListItemAddPairBinding>(binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<ListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as? BaseDataBindingViewHolder<ListItemAddPairBinding> ?: return
        viewHolder.binding?.apply {
            this.viewModel = this@AddPairAdapterDelegate.viewModel
        }
    }
}