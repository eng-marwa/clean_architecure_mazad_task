package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.OptionItemLayoutBinding

class Sub2Adapter(private val context: Context) :
    RecyclerView.Adapter<Sub2Adapter.OptionsVH>() {
    private val TAG = "OptionsAdapter"
    var list = ArrayList<OptionsData>()

    inner class OptionsVH(itemView: OptionItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val optionDropDown = itemView.optionDropDown
        val optionLayout = itemView.optionLayout
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
    }

//    private fun setupSubOptionRV(holder: OptionsVH) {
//        val linearLayoutManager = LinearLayoutManager(context)
//        holder.subOptionsRV.layoutManager =
//            linearLayoutManager
//        holder.subOptionsRV.addItemDecoration(
//            DividerItemDecoration(
//                context,
//                linearLayoutManager.orientation
//            )
//        )
//        val subAdapter = Sub2Adapter(context, data)
//        holder.subOptionsRV.adapter = subAdapter
//
//    }

    private fun setupDropDown(holder: OptionsVH, item: OptionsData) {
        val subOptionsAdapter = SubOptionAdapter(context = context)
        subOptionsAdapter.setData(item.options)
        holder.optionDropDown.setAdapter(subOptionsAdapter)
        holder.optionDropDown.setOnItemClickListener { parent, view, position, id ->
            val optionsData = parent.getItemAtPosition(position) as SubCategoryOptions
            holder.optionDropDown.setText(optionsData.name, false)

        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: ArrayList<OptionsData>) {
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

}