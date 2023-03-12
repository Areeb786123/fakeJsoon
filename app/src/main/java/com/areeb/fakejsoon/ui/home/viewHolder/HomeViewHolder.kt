package com.areeb.fakejsoon.ui.home.viewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.databinding.HomeItemBinding
import com.bumptech.glide.Glide

class HomeViewHolder(private val binding: HomeItemBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    lateinit var dataDto: Data
    fun bind(
        dataDto: Data,
    ) {
        this.dataDto = dataDto
        if (dataDto.avatar.isNotEmpty()) {
            binding.userImage.visibility = View.VISIBLE
            Glide.with(context).load(dataDto.avatar).into(binding.userImage)
        }
        binding.userName.text = dataDto.first_name
    }
}
