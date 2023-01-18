package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.main_categiores.Categories
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.ItemRowBinding
import com.marwa.mazadattask.databinding.OptionItemLayoutBinding
import com.marwa.mazadattask.presentation.main_categories.Item
import com.marwa.mazadattask.presentation.main_categories.view.MainFragment1

class CategoriesAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoriesAdapter.OptionsVH>() {
    var list = ArrayList<Categories>()
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
            Item.SelectedItemLiveData.value = item
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<Categories>?) {
        clear()
        //  list.add(0, SubCategoryOptions(name = "اخرى", id = -1))
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }

    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun modifyList(list : ArrayList<Categories>) {
        this.setData(list,)
        notifyDataSetChanged()
    }

    fun filter(query: CharSequence?) {
        val newList = ArrayList<Categories>()
        if(!query.isNullOrEmpty()) {
            newList.addAll(list.filter {
                it.name!!.toLowerCase().contains(query.toString().toLowerCase())  })
        } else {
            newList.addAll(list)
        }

        modifyList(newList)
    }


}