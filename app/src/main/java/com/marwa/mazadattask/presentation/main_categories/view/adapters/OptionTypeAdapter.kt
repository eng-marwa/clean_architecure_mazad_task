package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.ItemRowBinding
import com.marwa.mazadattask.databinding.SubItemsRowBinding
import com.marwa.mazadattask.presentation.main_categories.Item

class OptionTypeAdapter(private val context: Context) :
    RecyclerView.Adapter<OptionTypeAdapter.OptionsVH>() {
    private var adapterPosition: Int = -1
    var list = ArrayList<OptionsData>()
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
            Item.SelectedTypeLiveData.value = Pair(item, holder.adapterPosition)
            adapterPosition = holder.adapterPosition
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<OptionsData>) {
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

    fun updateSubLevelAdapter(name: String?) {
        list[adapterPosition].name = name
        notifyItemChanged(adapterPosition)
    }


}