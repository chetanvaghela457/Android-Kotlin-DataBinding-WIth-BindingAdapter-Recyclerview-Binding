package com.databinding.databindingwithbindingadapterrecyclerviewbinding.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.databinding.ItemProductsBinding
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.model.ProductData
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.viewmodel.ProductsViewModel

class ProductsItemAdapter(
    val temperatureLogsViewModel: ProductsViewModel
) :
    ListAdapter<ProductData, RecyclerView.ViewHolder>(DiffCallback)  {

    inner class ProductsItemViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(nasaData: ProductData, position: Int) {
            binding.productsModel = nasaData
            binding.viewModel = temperatureLogsViewModel
            binding.executePendingBindings()
        }
    }


    object DiffCallback : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adjustViewHolder = holder as ProductsItemViewHolder
        val fridgeItem = getItem(position)

        fridgeItem.let {
            adjustViewHolder.onBind(it, position)
        }
    }
}