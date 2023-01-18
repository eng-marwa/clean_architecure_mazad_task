package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.ItemRowBinding
import com.marwa.mazadattask.presentation.main_categories.Item

class SubCategoryOptionsAdapter(private val context: Context) :
    RecyclerView.Adapter<SubCategoryOptionsAdapter.OptionsVH>() {
    private var position: Int = 0
    var list = ArrayList<SubCategoryOptions>()
    private val TAG = "OptionsAdapter"

    inner class OptionsVH(itemView: ItemRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionLayout = itemView.lbMainCategoryItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsVH = OptionsVH(
        ItemRowBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun onBindViewHolder(holder: OptionsVH, position: Int) {
        val item = list[position]
        holder.optionLayout.text = item.name
        holder.itemView.setOnClickListener {
            Item.SelectedSubCategoryOptionLiveData.value = Pair(item, position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<SubCategoryOptions>?, position: Int) {
        clear()
        this.position = position
        this.list.add(0, SubCategoryOptions(name = "اخرى", id = -1))
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }

    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun modifyList(list: ArrayList<SubCategoryOptions>) {
        this.setData(list, position)
        notifyDataSetChanged()
    }

    fun filter(query: CharSequence?) {
        val newList = ArrayList<SubCategoryOptions>()
        if (!query.isNullOrEmpty()) {
            newList.addAll(list.filter {
                it.name!!.toLowerCase().contains(query.toString().toLowerCase())
            })
        } else {
            newList.addAll(list)
        }

        modifyList(newList)
    }


}