package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.databinding.SubItemsRowBinding
import com.marwa.mazadattask.presentation.main_categories.Item

class ItemOptionsAdapter(private val context: Context) :
    RecyclerView.Adapter<ItemOptionsAdapter.OptionsVH>() {
    private var adapterPosition: Int = -1
    var list = ArrayList<SubCategoriesData>()
    private val TAG = "OptionsAdapter"

    inner class OptionsVH(itemView: SubItemsRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionLayout = itemView.lbMainCategoryItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsVH = OptionsVH(
        SubItemsRowBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun onBindViewHolder(holder: OptionsVH, position: Int) {
        val item = list[position]
        holder.optionLayout.text = item.name
        holder.itemView.setOnClickListener {
            Item.SelectedSubCategoriesLiveData.value = Pair(item, holder.adapterPosition)
            adapterPosition = holder.adapterPosition

            if (adapterPosition != holder.adapterPosition) {
                currentPosition = position
                holder.itemView.performClick()

            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<SubCategoriesData>) {
        clear()
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }

    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun updateAdapter(name: String?) {
        list[0].name=name
        notifyDataSetChanged()

    }

    companion object {
        var currentPosition = -1

    }
}