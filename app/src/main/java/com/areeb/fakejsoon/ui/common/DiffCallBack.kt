package com.areeb.fakejsoon.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.areeb.fakejsoon.data.models.Data

class DiffCallBack : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }
}
