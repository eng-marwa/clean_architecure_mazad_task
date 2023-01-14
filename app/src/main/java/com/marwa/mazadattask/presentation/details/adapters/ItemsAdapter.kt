package com.marwa.mazadattask.presentation.details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.marwa.mazadattask.databinding.ItemLayoutBinding

class ItemsAdapter(private val context: Context) : RecyclerView.Adapter<ItemsAdapter.ItemVH>() {
    inner class ItemVH(itemView: ItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH = ItemVH(
        ItemLayoutBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ItemVH, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

}