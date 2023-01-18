package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.ItemRowBinding
import com.marwa.mazadattask.presentation.main_categories.Item
import java.lang.Character.toLowerCase
import java.util.*
import kotlin.collections.ArrayList

class SubItemOptionsAdapter (
    private val context: Context,
) :
    RecyclerView.Adapter<SubItemOptionsAdapter.OptionsVH>() {
    private var level: Int=0
    private var adapterPosition: Int = 0
    var list = ArrayList<SubCategoryOptions>()
    private val TAG = "SubAdapter"
    var position = 0
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
                when(level){
                    1-> Item.ModalOptionsItemsLiveData.value = item
                    2-> Item.TypedOptionsItemsLiveData.value = item
                }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<SubCategoryOptions>, position: Int, level: Int) {
        clear()
        this.level = level
        this.position = position
        this.list.add(0, SubCategoryOptions(id = -1,"اخرى"))
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }


    fun modifyList(list : ArrayList<SubCategoryOptions>) {
        this.setData(list,position,level)
        notifyDataSetChanged()
    }

    fun filter(query: CharSequence?) {
        val newList = ArrayList<SubCategoryOptions>()
        if(!query.isNullOrEmpty()) {
            newList.addAll(list.filter {
                it.name!!.toLowerCase().contains(query.toString().toLowerCase())  })
        } else {
            newList.addAll(list)
        }

        modifyList(newList)
    }

}