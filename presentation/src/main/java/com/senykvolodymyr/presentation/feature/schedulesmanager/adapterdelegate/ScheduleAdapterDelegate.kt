package com.senykvolodymyr.presentation.feature.schedulesmanager.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.senykvolodymyr.core.recyclerview.listitem.ListItem
import com.senykvolodymyr.core.recyclerview.viewholder.BaseDataBindingViewHolder
import com.senykvolodymyr.presentation.databinding.ListItemScheduleBinding
import com.senykvolodymyr.presentation.feature.common.entity.ScheduleUi
import com.senykvolodymyr.presentation.feature.schedulesmanager.SchedulesManagerViewModel

class ScheduleAdapterDelegate(
    private val viewModel: SchedulesManagerViewModel
) : AdapterDelegate<List<ListItem>>() {

    override fun isForViewType(items: List<ListItem>, position: Int): Boolean =
        items[position] is ScheduleUi

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemScheduleBinding.inflate(inflater, parent, false)
        return BaseDataBindingViewHolder<ListItemScheduleBinding>(binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<ListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as? BaseDataBindingViewHolder<ListItemScheduleBinding> ?: return
        val model = items[position] as? ScheduleUi ?: return
        viewHolder.binding?.apply {
            this.model = model
            this.viewModel = this@ScheduleAdapterDelegate.viewModel
        }
    }
}
