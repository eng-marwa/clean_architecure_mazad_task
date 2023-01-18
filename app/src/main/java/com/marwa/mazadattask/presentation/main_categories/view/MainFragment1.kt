package com.marwa.mazadattask.presentation.main_categories.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwa.mazadattask.R
import com.marwa.mazadattask.data.model.main_categiores.Categories
import com.marwa.mazadattask.data.model.main_categiores.Children
import com.marwa.mazadattask.databinding.FragmentMain1Binding
import com.marwa.mazadattask.presentation.main_categories.Item
import com.marwa.mazadattask.presentation.main_categories.view.adapters.ItemOptionsAdapter
import com.marwa.mazadattask.presentation.main_categories.view.adapters.SubCategoryDataAdapter
import com.marwa.mazadattask.presentation.main_categories.viewmodel.CategoriesViewModel
import com.marwa.mazadattask.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainFragment1 : Fragment() {
    private var level = 0
    private var mainCategory: Categories? = null
    private var item: Children? = null
    private var subCategories: ArrayList<Children> = arrayListOf()
    private lateinit var bottomSheet: ItemsBottomSheetFragment
    private lateinit var subBottomSheet: SubItemsBottomSheetFragment
    private lateinit var subItemOptionBottomSheet: SubItemOptionsBottomSheetFragment
    private lateinit var optionBottomSheet: ItemOptionsBottomSheetFragment
    private lateinit var subCategoryOptionsAdapter: SubCategoryDataAdapter
    private lateinit var itemOptionsAdapter: ItemOptionsAdapter
    private val viewModel by viewModels<CategoriesViewModel>()

    private var _binding: FragmentMain1Binding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMain1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
        observeAdapter()
    }

    private fun observeAdapter() {
        Item.SelectedItemLiveData.observe(viewLifecycleOwner) {
            mainCategory = it
            binding.btnMainCategory.text = it.name
            subCategories = it.children
            prepareChild()
        }

        Item.SelectedSubItemLiveData.observe(viewLifecycleOwner) {
            binding.btnSubCategory.text = it.name
            item = it
            prepareSubCategoryOptions(it.id!!)

        }
    }

    private fun prepareSubCategoryOptions(id: Int) {
        if (::subBottomSheet.isInitialized) {
            subBottomSheet.dismiss()
        }
        binding.rvItems.visibility = View.VISIBLE
        viewModel.getSubCategoriesOptions(id)
    }

    private fun prepareChild() {
        if (::bottomSheet.isInitialized) {
            bottomSheet.dismiss()
        }
        binding.btnSubCategory.text = "الفئة الفرعية"
        binding.btnSubCategory.callOnClick()
        binding.rvItems.visibility = View.GONE

    }


    private fun observeViewModel() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) {
                it?.fold({
                    it.data?.categories?.let { categories ->
                        bottomSheet =
                            ItemsBottomSheetFragment.newInstance(categories, "الفئة الأساسية")
                        if(!bottomSheet.isVisible) {
                            bottomSheet.show(childFragmentManager, "dialog")
                        }
                    }
                }, {
                    showToast(it?.getMessage())
                })

        }

        viewModel.subCategoriesOptionsLiveData.observe(viewLifecycleOwner) {
            it?.fold({
                it.data?.let { subCategoriesData ->
                    subCategoryOptionsAdapter.setData(subCategoriesData)
                }
            }, {
                showToast(it?.getMessage())
            })
        }

        Item.SelectedSubCategoriesLiveData.observe(viewLifecycleOwner) {
                it?.let { pair ->
                    pair.first.let {
                        subItemOptionBottomSheet =
                            SubItemOptionsBottomSheetFragment.newInstance(
                                it.SubCategoryOptions,
                                it.name!!,
                                pair.second
                            )
                        if(!subItemOptionBottomSheet.isVisible) {
                            subItemOptionBottomSheet.show(childFragmentManager, "dialog")
                        }
                    }
                }

        }
        Item.SelectedSubCategoryOptionLiveData.observe(viewLifecycleOwner) {
            var other = false
            it?.let { pair ->
                if (pair.first.id == -1) {
                    other = true
                } else {
                    other=false
                    if (it.first.child == true) {
                        level = 1
                        viewModel.getOptions(it.first.id!!)
                    }
                }
                subCategoryOptionsAdapter.updateAdapter(pair.first.name,other)
            }
        }

        viewModel.optionsLiveData.observe(viewLifecycleOwner) {
            it?.fold({
                if (level == 1) {
                    it.data?.let { optionData ->
                        subCategoryOptionsAdapter.setOptions(optionData)
                    }
                } else {
                    it.data?.let { optionData ->
                        subCategoryOptionsAdapter.setSubOptions(optionData)
                    }
                }
            }, {
                showToast(it?.getMessage())
            })
        }
        Item.OptionsItemsLiveData.observe(viewLifecycleOwner) {

                it?.let { pair ->
                    optionBottomSheet = ItemOptionsBottomSheetFragment.newInstance(
                        pair.first.options,
                        pair.first.name!!,
                        pair.second, 1
                    )
                    if(!optionBottomSheet.isVisible) {
                    optionBottomSheet.show(childFragmentManager, "dialog")
                }
            }
        }
        Item.SelectedTypeLiveData.observe(viewLifecycleOwner) {

                it?.let { pair ->
                    optionBottomSheet = ItemOptionsBottomSheetFragment.newInstance(
                        pair.first.options,
                        pair.first.name!!,
                        pair.second,
                        2
                    )
                    if(!optionBottomSheet.isVisible) {
                    optionBottomSheet.show(childFragmentManager, "dialog")
                }
            }
        }
        Item.ModalOptionsItemsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                    if (it.id == -1) {
                    } else {
                        if (it.child == true) {
                            level = 2
                            viewModel.getOptions(it.id!!)
                        }
                    }
                    subCategoryOptionsAdapter.updateSubAdapter(it.name)
                }

        }
        Item.TypedOptionsItemsLiveData.observe(viewLifecycleOwner){
            it?.let {
                if (it.id == -1) {
                    //add text
                } else {
                    if (it.child == true) {
                        level = 2
                        viewModel.getOptions(it.id!!)
                    }
                }
                subCategoryOptionsAdapter.updateSubLevelAdapter(it.name)
            }

        }
    }
        private fun initViews() {
            binding.btnNavigateToDetails.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_main_to_detailsFragment)
            }
            binding.btnMainCategory.setOnClickListener {
                viewModel.getCategories()
            }

            binding.btnSubCategory.setOnClickListener {
                if (subCategories.isNotEmpty()) {
                    subBottomSheet =
                        SubItemsBottomSheetFragment.newInstance(subCategories, "الفئة الفرعية")
                    if(!subBottomSheet.isVisible)
                    subBottomSheet.show(childFragmentManager, "dialog")
                }
            }
            setupItemsRV()
        }

        private fun setupItemsRV() {
            context?.let { context ->
                val linearLayoutManager = LinearLayoutManager(context)
                binding.rvItems.layoutManager =
                    linearLayoutManager
                subCategoryOptionsAdapter = SubCategoryDataAdapter(context)
                binding.rvItems.adapter = subCategoryOptionsAdapter

            }
        }


        companion object {

            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                MainFragment1().apply {
                    arguments = Bundle().apply {

                    }
                }
        }
    }