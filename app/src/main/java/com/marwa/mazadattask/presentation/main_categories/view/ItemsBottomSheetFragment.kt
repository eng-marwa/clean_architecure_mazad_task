package com.marwa.mazadattask.presentation.main_categories.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marwa.mazadattask.R
import com.marwa.mazadattask.data.model.main_categiores.Categories
import com.marwa.mazadattask.databinding.FragmentItemsBottomSheetBinding
import com.marwa.mazadattask.presentation.main_categories.Item.SelectedItemLiveData
import com.marwa.mazadattask.presentation.main_categories.view.adapters.CategoriesAdapter
import com.marwa.mazadattask.util.showToast


class ItemsBottomSheetFragment : BottomSheetDialogFragment() {
    private  val TAG = "ItemsBottomSheetFragmen"
    private var title: String = ""
    private var mainCategories: ArrayList<Categories>? = null
    private lateinit var adapter: CategoriesAdapter
    private var _binding: FragmentItemsBottomSheetBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        isCancelable = false

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mainCategories = it.getParcelableArrayList<Categories>("MainCategories")
            title = it.getString("Title","")
        }
        initViews()
    }


    private fun initViews() {
        setupItemsRV()
        adapter.setData(mainCategories)
        binding.lbTitle.text = title
        binding.btnCloseDialog.setOnClickListener {
            dismiss()
        }
    }

    private fun setupItemsRV() {
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context)
            binding.rvItems.layoutManager =
                linearLayoutManager
            binding.rvItems.addItemDecoration(
                DividerItemDecoration(
                    context,
                    linearLayoutManager.orientation
                )
            )
            adapter = CategoriesAdapter(context)
            binding.rvItems.adapter = adapter

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(mainCategories: ArrayList<Categories>,title:String) =
            ItemsBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("MainCategories", mainCategories)
                    putString("Title",title)
                }
            }
    }
}