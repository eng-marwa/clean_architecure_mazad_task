package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.databinding.SubItemsRowBinding
import com.marwa.mazadattask.presentation.main_categories.Item

class SubAdapter(
    private val context: Context,
) :
    RecyclerView.Adapter<SubAdapter.OptionsVH>() {
    private var adapterPosition: Int = 0
    var list = ArrayList<OptionsData>()
    private val TAG = "SubAdapter"
    private lateinit var adapter: OptionTypeAdapter


    inner class OptionsVH(itemView: SubItemsRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionLayout = itemView.lbMainCategoryItem
        val rvOptions = itemView.rvOptions
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
          Item.OptionsItemsLiveData.value = Pair(item,holder.adapterPosition)
            adapterPosition = holder.adapterPosition
        }
        setupOptionsRV(holder)
    }
    private fun setupOptionsRV(holder: OptionsVH) {
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context)
            holder.rvOptions.layoutManager =
                linearLayoutManager
            adapter = OptionTypeAdapter(context)
            holder.rvOptions.adapter = adapter

        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<OptionsData>) {
        clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun updateAdapter(name: String?) {
        list[adapterPosition].name=name!!
        notifyItemChanged(adapterPosition)
    }

    fun setAdapterData(it: ArrayList<OptionsData>) {
        adapter.setData(it)
    }

    fun updateSubLevelAdapter(name: String?) {
        if(::adapter.isInitialized){
            adapter.updateSubLevelAdapter(name)
        }
    }

}