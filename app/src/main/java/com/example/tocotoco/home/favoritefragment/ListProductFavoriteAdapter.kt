package com.example.tocotoco.home.favoritefragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tocotoco.basekotlin.extensions.decimalFormatted
import com.example.tocotoco.databinding.ItemListProductFavoriteBinding
import com.example.tocotoco.model.FavoriteProductsResult.FavoriteProductsResultModel

class ListProductFavoriteAdapter :
    ListAdapter<FavoriteProductsResultModel, ListProductFavoriteAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<FavoriteProductsResultModel>() {
            override fun areItemsTheSame(
                oldItem: FavoriteProductsResultModel,
                newItem: FavoriteProductsResultModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavoriteProductsResultModel,
                newItem: FavoriteProductsResultModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListProductFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemListProductFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(item: FavoriteProductsResultModel) = binding.run {
                tvTitle.text = item.productName
                tvPrice.text = "${item.priceAfterDiscount.toInt().decimalFormatted()} đ"
                Glide.with(root.context)
                    .load(item.displayImage)
                    .fitCenter()
                    .into(imgProduct)
            }
    }
}