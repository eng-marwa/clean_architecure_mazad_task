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
import com.marwa.mazadattask.data.model.main_categiores.Children
import com.marwa.mazadattask.databinding.FragmentItemsBottomSheetBinding
import com.marwa.mazadattask.presentation.main_categories.Item.SelectedItemLiveData
import com.marwa.mazadattask.presentation.main_categories.view.adapters.CategoriesAdapter
import com.marwa.mazadattask.presentation.main_categories.view.adapters.SubCategoriesAdapter
import com.marwa.mazadattask.util.showToast


class SubItemsBottomSheetFragment : BottomSheetDialogFragment() {
    private val TAG = "ItemsBottomSheetFragmen"
    private var title: String = ""
    private var subCategories: ArrayList<Children>? = null
    private lateinit var adapter: SubCategoriesAdapter
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
            subCategories = it.getParcelableArrayList<Children>("SubCategories")
            title = it.getString("Title", "")
        }
        initViews()
    }


    private fun initViews() {
        setupItemsRV()
        adapter.setData(subCategories)
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
            adapter = SubCategoriesAdapter(context)
            binding.rvItems.adapter = adapter

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(subCategories: ArrayList<Children>, title: String) =
            SubItemsBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("SubCategories", subCategories)
                    putString("Title", title)
                }
            }
    }
}