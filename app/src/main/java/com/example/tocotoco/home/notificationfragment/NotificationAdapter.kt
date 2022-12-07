package com.example.tocotoco.home.notificationfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tocotoco.databinding.ItemNotificationBinding
import com.example.tocotoco.model.ProductsResult.ProductsResultModel

class NotificationAdapter(
    private val onItemClick: (Int) -> Unit
) : ListAdapter<ProductsResultModel, NotificationAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<ProductsResultModel>() {
        override fun areItemsTheSame(
            oldItem: ProductsResultModel,
            newItem: ProductsResultModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductsResultModel,
            newItem: ProductsResultModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            onItemClick(getItem(position).id)
        }
    }

    class ViewHolder(val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductsResultModel) = binding.run {

        }
    }
}