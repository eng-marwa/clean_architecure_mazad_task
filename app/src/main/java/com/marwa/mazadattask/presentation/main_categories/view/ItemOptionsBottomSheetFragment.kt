package com.marwa.mazadattask.presentation.main_categories.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marwa.mazadattask.R
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions
import com.marwa.mazadattask.databinding.FragmentItemsBottomSheetBinding
import com.marwa.mazadattask.presentation.main_categories.view.adapters.SubItemOptionsAdapter


class ItemOptionsBottomSheetFragment : BottomSheetDialogFragment() {
    private val TAG = "ItemsBottomSheetFragmen"
    private var title: String = ""
    private var position: Int = 0
    private var level: Int = 0
    private var options: ArrayList<SubCategoryOptions>? = null
    private lateinit var adapter: SubItemOptionsAdapter
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
            options = it.getParcelableArrayList<SubCategoryOptions>("Options")
            title = it.getString("Title", "")
            position = it.getInt("Position", 0)
            level = it.getInt("Level", 0)

        }
        initViews()
    }


    private fun initViews() {
        setupItemsRV()
        options?.let {
            adapter.setData(it, position,level)
        }
        binding.lbTitle.text = title
        binding.btnCloseDialog.setOnClickListener {
            dismiss()
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (binding.rvItems.adapter as SubItemOptionsAdapter).filter(newText)
                return true
            }
        })
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
            adapter = SubItemOptionsAdapter(context)
            binding.rvItems.adapter = adapter

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(
            options: ArrayList<SubCategoryOptions>,
            title: String,
            position: Int,
            level: Int
        ) =
            ItemOptionsBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("Options", options)
                    putString("Title", title)
                    putInt("Position", position!!)
                    putInt("Level", level)
                }
            }
    }
}