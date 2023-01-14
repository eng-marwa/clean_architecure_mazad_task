package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.OptionItemLayoutBinding

class SubAdapter(
    private val context: Context,
) :
    RecyclerView.Adapter<SubAdapter.OptionsVH>() {
    private lateinit var sub2Adapter: Sub2Adapter
    var list = ArrayList<OptionsData>()
    private val TAG = "SubAdapter"


    inner class OptionsVH(itemView: OptionItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionDropDown = itemView.optionDropDown
        val optionLayout = itemView.optionLayout
        val otherLayout = itemView.otherLayout
        val subOptionsRV = itemView.subOptionsRV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsVH = OptionsVH(
        OptionItemLayoutBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun onBindViewHolder(holder: OptionsVH, position: Int) {
        val item = list[position]
        holder.optionLayout.hint = item.name
        holder.optionLayout.placeholderText = item.name
        setupDropDown(holder, item)
//        setupSubOptionRV(holder)
    }

    private fun setupSubOptionRV(holder: OptionsVH, data: ArrayList<OptionsData>) {
        val linearLayoutManager = LinearLayoutManager(context)
        holder.subOptionsRV.layoutManager =
            linearLayoutManager
        holder.subOptionsRV.addItemDecoration(
            DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )
        )
        sub2Adapter = Sub2Adapter(context,data)
        holder.subOptionsRV.adapter = sub2Adapter
    }

    private fun setupDropDown(holder: OptionsVH, item: OptionsData) {
        val nestedOptionsAdapter = NestedSubOptionsAdapter(context = context)
        nestedOptionsAdapter.setData(item.options)
        holder.optionDropDown.setAdapter(nestedOptionsAdapter)
        holder.optionDropDown.setOnItemClickListener { parent, view, position, id ->
            val subCategoryOptions = parent.getItemAtPosition(position) as SubCategoryOptions
//            if (subCategoryOptions.id == -1) {
//                holder.otherLayout.visibility = View.VISIBLE
//            } else if (subCategoryOptions.child == true) {
//               setupSubOptionRV(holder)
//                holder.otherLayout.visibility = View.GONE
//
//
//            }
            holder.optionDropDown.setText(subCategoryOptions.name, false)

        }
    }

    private fun observeViewModel(holder: OptionsVH) {

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


}