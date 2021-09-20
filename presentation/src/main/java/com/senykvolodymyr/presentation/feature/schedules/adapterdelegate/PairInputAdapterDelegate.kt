package com.senykvolodymyr.presentation.feature.schedules.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.senykvolodymyr.core.recyclerview.listitem.ListItem
import com.senykvolodymyr.core.recyclerview.viewholder.BaseDataBindingViewHolder
import com.senykvolodymyr.presentation.databinding.ListItemPairInputBinding
import com.senykvolodymyr.presentation.feature.common.entity.PairUi
import com.senykvolodymyr.presentation.feature.schedules.OneDayScheduleViewModel

class PairInputAdapterDelegate(
    private val viewModel: OneDayScheduleViewModel
) : AdapterDelegate<List<ListItem>>() {

    override fun isForViewType(items: List<ListItem>, position: Int): Boolean =
        items[position] is PairUi

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPairInputBinding.inflate(inflater, parent, false)
        return BaseDataBindingViewHolder<ListItemPairInputBinding>(binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<ListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as? BaseDataBindingViewHolder<ListItemPairInputBinding> ?: return
        val model = items[position] as? PairUi ?: return
        viewHolder.binding?.apply {
            this.model = model
            this.viewModel = this@PairInputAdapterDelegate.viewModel

            inputPairStartTime.background.alpha = 80
            inputPairEndTime.background.alpha = 80
            inputPairName.background.alpha = 80
            inputPairTeacher.background.alpha = 80
            spinnerPairType.background.alpha = 80
            inputPairPlace.background.alpha = 80
            inputPairNotes.background.alpha = 80
        }
    }
}
