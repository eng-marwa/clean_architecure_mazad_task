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
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.OptionItemLayoutBinding

class OptionsAdapter(private val context: Context) :
    RecyclerView.Adapter<OptionsAdapter.OptionsVH>() {
    private lateinit var subAdapter: SubAdapter
    var list = ArrayList<SubCategoriesData>()
    val childOptionLiveData = MutableLiveData<Int>()
    private val TAG = "OptionsAdapter"

    inner class OptionsVH(itemView: OptionItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionDropDown = itemView.optionDropDown
        val optionLayout = itemView.optionLayout
        val subOptionsRV = itemView.subOptionsRV
        val otherLayout = itemView.otherLayout
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
        setupDropDown(holder, item.options)
    }

    private fun setupSubOptionRV(holder: OptionsVH) {
        val linearLayoutManager = LinearLayoutManager(context)
        holder.subOptionsRV.layoutManager =
            linearLayoutManager
        holder.subOptionsRV.addItemDecoration(
            DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )
        )
        subAdapter = SubAdapter(context)
        holder.subOptionsRV.adapter = subAdapter
    }

    private fun setupDropDown(holder: OptionsVH, item: ArrayList<SubCategoryOptions>) {
        val subOptionsAdapter = SubOptionAdapter(context = context)
        subOptionsAdapter.setData(item)
        holder.optionDropDown.setAdapter(subOptionsAdapter)
        holder.optionDropDown.setOnItemClickListener { parent, view, position, id ->
            val subCategoryOptions = parent.getItemAtPosition(position) as SubCategoryOptions
            if (subCategoryOptions.id == -1) {
                holder.otherLayout.visibility = View.VISIBLE
            } else if (subCategoryOptions.child == true) {
                setupSubOptionRV(holder)
                holder.otherLayout.visibility = View.GONE
                childOptionLiveData.value = subCategoryOptions.id!!
            }
            holder.optionDropDown.setText(subCategoryOptions.name, false)

        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<SubCategoriesData>) {
        Log.d(TAG, "setData: ${list[0].name}")
        clear()
        //  list.add(0, SubCategoryOptions(name = "اخرى", id = -1))
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun setSubOptions(data: ArrayList<OptionsData>, level: String) {
        if (level == "Parent") {
            if (::subAdapter.isInitialized) {
                subAdapter.setData(data)
            }
        }else{
            createChildRV(data)
        }
    }

    private fun createChildRV(data: ArrayList<OptionsData>) {
        subAdapter.createChildRV(data)
//        val recyclerView = RecyclerView(context)
//        val linearLayoutManager = LinearLayoutManager(context)
//        recyclerView.layoutManager =
//            linearLayoutManager
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                context,
//                linearLayoutManager.orientation
//            )
//        )
//        val subAdapter = Sub2Adapter(context,data)
//        recyclerView.adapter = subAdapter
    }


}