package com.areeb.fakejsoon.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.areeb.fakejsoon.data.models.common.UserModel

class DiffCallBack : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }
}
