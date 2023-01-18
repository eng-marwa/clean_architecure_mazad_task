package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.databinding.SubItemsRowBinding
import com.marwa.mazadattask.presentation.main_categories.Item

class SubCategoryDataAdapter(private val context: Context) :
    RecyclerView.Adapter<SubCategoryDataAdapter.OptionsVH>() {
    private var hint: String = ""
    private var textVisible: Boolean = false
    private lateinit var adapter: SubAdapter
    private var adapterPosition: Int = -1
    var list = ArrayList<SubCategoriesData>()
    private val TAG = "OptionsAdapter"

    inner class OptionsVH(itemView: SubItemsRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionLayout = itemView.lbMainCategoryItem
        val rvOptions = itemView.rvOptions
        val frText = itemView.frText
        val lbHintItem = itemView.lbHintItem
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

        }
        setupOptionsRV(holder)
        if (textVisible) {
            holder.frText.visibility = View.VISIBLE
        } else {
            holder.frText.visibility = View.GONE
        }

    }

    private fun setupOptionsRV(holder: OptionsVH) {
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context)
            holder.rvOptions.layoutManager =
                linearLayoutManager
            adapter = SubAdapter(context)
            holder.rvOptions.adapter = adapter

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

    fun updateAdapter(name: String?, other: Boolean) {
        list[adapterPosition].name = name
        this.textVisible = other
        notifyItemChanged(adapterPosition)

    }

    fun setOptions(it: ArrayList<OptionsData>) {
        if (::adapter.isInitialized) {
            adapter.setData(it)

        }
    }

    fun updateSubAdapter(name: String?) {
        if (::adapter.isInitialized) {
            adapter.updateAdapter(name)
        }
    }

    fun setSubOptions(optionData: ArrayList<OptionsData>) {
        if (::adapter.isInitialized) {
            adapter.setAdapterData(optionData)

        }
    }

    fun updateSubLevelAdapter(name: String?) {
        if (::adapter.isInitialized) {
            adapter.updateSubLevelAdapter(name)
        }
    }


}