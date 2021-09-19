package com.senyk.volodymyr.schedulesapp.presentation.core.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.senyk.volodymyr.schedulesapp.R
import com.senyk.volodymyr.schedulesapp.databinding.ListItemMessageBinding
import com.senyk.volodymyr.schedulesapp.presentation.core.customview.MessageType.ERROR
import com.senyk.volodymyr.schedulesapp.presentation.core.customview.MessageType.WARNING
import com.senyk.volodymyr.schedulesapp.presentation.core.provider.AttrValuesProvisionUtil
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.listitem.ListItem
import com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.viewholder.BaseDataBindingViewHolder

class MessageAdapterDelegate : AdapterDelegate<List<ListItem>>() {

    override fun isForViewType(items: List<ListItem>, position: Int): Boolean =
        items[position] is MessageUi

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMessageBinding.inflate(inflater, parent, false)
        return BaseDataBindingViewHolder<ListItemMessageBinding>(binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        items: List<ListItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val model = items[position] as? MessageUi
        val viewHolder = holder as? BaseDataBindingViewHolder<ListItemMessageBinding> ?: return
        viewHolder.binding?.apply {
            val attrRes = when (model?.messageType) {
                ERROR -> R.attr.colorError
                WARNING -> R.attr.colorWarning
                null -> R.attr.colorWarning
            }
            this.outputMessage.setTextColor(
                AttrValuesProvisionUtil.getThemeColorInt(
                    this.outputMessage.context.theme, attrRes
                )
            )
            this.model = model
        }
    }
}
