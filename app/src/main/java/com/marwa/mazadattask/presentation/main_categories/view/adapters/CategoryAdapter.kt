package com.marwa.mazadattask.presentation.main_categories.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.marwa.mazadattask.R
import com.marwa.mazadattask.data.model.main_categiores.Categories
import com.marwa.mazadattask.databinding.CategoryRowBinding

class CategoryAdapter(context: Context) : ArrayAdapter<Categories>(context, R.layout.category_row) {
    var list = ArrayList<Categories>()
    private  val TAG = "CategoryAdapter"

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Categories {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var viewHolder: ViewHolder? = null
        var view = convertView
        if (view == null) {
            val binding =
                CategoryRowBinding.inflate(LayoutInflater.from(context), parent, false)
            viewHolder = ViewHolder(binding)
            view = binding.root
            view?.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        val item = getItem(position).name
        viewHolder.lbCategoryName.text = item
        return view!!
    }

    fun setData(list: List<Categories>) {
        clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun clear() {
        list.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: CategoryRowBinding) {
        val lbCategoryName = view.lbCategoryName
    }


}