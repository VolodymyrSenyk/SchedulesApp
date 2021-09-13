package com.senyk.volodymyr.schedulesapp.presentation.feature.schedulesmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.senyk.volodymyr.schedulesapp.databinding.ListItemScheduleDataBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.viewholder.BaseDataBindingViewHolder
import com.senyk.volodymyr.schedulesapp.presentation.feature.common.entity.ScheduleUi

class SchedulesOutputAdapterDelegate(
    private val viewModel: SchedulesManagerViewModel
) : AdapterDelegate<List<ListItem>>() {

    override fun isForViewType(items: List<ListItem>, position: Int): Boolean =
        items[position] is ScheduleUi

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemScheduleDataBinding.inflate(inflater, parent, false)
        return BaseDataBindingViewHolder<ListItemScheduleDataBinding>(binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<ListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as? BaseDataBindingViewHolder<ListItemScheduleDataBinding> ?: return
        val model = items[position] as? ScheduleUi ?: return
        viewHolder.binding?.apply {
            this.model = model
            this.viewModel = this@SchedulesOutputAdapterDelegate.viewModel
        }
    }
}
