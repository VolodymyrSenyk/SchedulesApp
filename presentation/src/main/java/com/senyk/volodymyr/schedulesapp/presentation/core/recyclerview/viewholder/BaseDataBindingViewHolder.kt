package com.senyk.volodymyr.schedulesapp.presentation.core.recyclerview.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

open class BaseDataBindingViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    val binding: T? = DataBindingUtil.bind(view)
}
