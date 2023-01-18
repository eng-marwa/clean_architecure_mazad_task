package com.marwa.mazadattask.presentation.main_categories.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marwa.mazadattask.R
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.FragmentItemsBottomSheetBinding
import com.marwa.mazadattask.presentation.main_categories.Item.SelectedSubCategoryOptionLiveData
import com.marwa.mazadattask.presentation.main_categories.view.adapters.SubCategoryOptionsAdapter


class SubItemOptionsBottomSheetFragment : BottomSheetDialogFragment() {
    private var position: Int = 0
    private val TAG = "ItemsBottomSheetFragmen"
    private var title: String = ""
    private var subCategoriesOptions: ArrayList<SubCategoryOptions>? = null
    private lateinit var adapter: SubCategoryOptionsAdapter
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
            subCategoriesOptions =
                it.getParcelableArrayList<SubCategoryOptions>("SubCategoriesOptions")
            title = it.getString("Title", "")
            position = it.getInt("Position", 0)
        }
        initViews()
    }



    private fun initViews() {
        setupItemsRV()
        adapter.setData(subCategoriesOptions,position)
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
            adapter = SubCategoryOptionsAdapter(context)
            binding.rvItems.adapter = adapter

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(
            subCategoriesOptions: ArrayList<SubCategoryOptions>,
            title: String,
            position: Int?
        ) =
            SubItemOptionsBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("SubCategoriesOptions", subCategoriesOptions)
                    putString("Title", title)
                    putInt("Position",position!!)
                }
            }
    }
}