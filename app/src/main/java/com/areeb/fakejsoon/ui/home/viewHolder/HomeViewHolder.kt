package com.areeb.fakejsoon.ui.home.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.databinding.HomeItemBinding

class HomeViewHolder(private val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var dataDto: Data
    fun bind(
        dataDto: Data,
    ) {
        this.dataDto = dataDto
        binding.userName.text = dataDto.first_name
    }
}
