package com.areeb.fakejsoon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.databinding.HomeItemBinding
import com.areeb.fakejsoon.ui.common.DiffCallBack
import com.areeb.fakejsoon.ui.home.viewHolder.HomeViewHolder

class HomeAdapter : PagingDataAdapter<Data, HomeViewHolder>(DiffCallBack()) {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return HomeViewHolder(binding)
    }
}
