package com.example.tocotoco.home.homefragment.productfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tocotoco.basekotlin.extensions.decimalFormatted
import com.example.tocotoco.databinding.ItemListTeaBinding
import com.example.tocotoco.model.ProductsByCategoryResultModel

class ListProductAdapter(
    private val onItemClick: (Int) -> Unit
) :
    ListAdapter<ProductsByCategoryResultModel, ListProductAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<ProductsByCategoryResultModel>() {
            override fun areItemsTheSame(
                oldItem: ProductsByCategoryResultModel,
                newItem: ProductsByCategoryResultModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProductsByCategoryResultModel,
                newItem: ProductsByCategoryResultModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListTeaBinding.inflate(
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

    class ViewHolder(val binding: ItemListTeaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ProductsByCategoryResultModel) = binding.run {
            tvTitle.text = item.name
            tvPrice.text = "${item.price.toInt().decimalFormatted()} đ"
            Glide.with(root.context)
                .load(item.displayimage)
                .fitCenter()
                .into(imgProduct)
        }
    }
}