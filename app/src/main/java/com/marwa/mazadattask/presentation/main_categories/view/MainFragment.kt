package com.marwa.mazadattask.presentation.main_categories.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwa.mazadattask.R
import com.marwa.mazadattask.data.model.main_categiores.Categories
import com.marwa.mazadattask.data.model.main_categiores.Children
import com.marwa.mazadattask.databinding.FragmentMainBinding
import com.marwa.mazadattask.presentation.main_categories.view.adapters.*
import com.marwa.mazadattask.presentation.main_categories.viewmodel.CategoriesViewModel
import com.marwa.mazadattask.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var oAdapter: OptionsAdapter
    private lateinit var suCategoryAdapter: SubCategoryAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!
    private val viewModel by viewModels<CategoriesViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            it?.fold({
                it.data?.categories?.let { categories -> categoryAdapter.setData(categories) }
            }, {
                showToast(it?.getMessage())
            })
        }

        viewModel.subCategoriesLiveData.observe(viewLifecycleOwner) {
            it?.fold({
                if (::oAdapter.isInitialized) {
                    oAdapter.setData(it.data)
                }
            }, {
                showToast(it?.getMessage())
            })
        }
        viewModel.optionsLiveData.observe(viewLifecycleOwner) {
            it?.fold({
               oAdapter.setSubOptions(it.data)
            }, {
                showToast(it?.getMessage())
            })
        }


    }



    private fun initViews() {
        //getCategories
        setupOptionsRV()
        setupCategoryDropDown()
        setupSubCategoryDropDown()
        viewModel.getCategories()

        binding.btnNavigateToDetails.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_main_to_detailsFragment)
        }
    }

    private fun setupOptionsRV() {
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context)
            binding.rvOptions.layoutManager =
                linearLayoutManager
            binding.rvOptions.addItemDecoration(
                DividerItemDecoration(
                    context,
                    linearLayoutManager.orientation
                )
            )
            oAdapter = OptionsAdapter(context)
            binding.rvOptions.adapter = oAdapter

        }
        observeAdapter()
    }


    private fun observeAdapter() {
        oAdapter.childOptionLiveData.observe(viewLifecycleOwner) {
            viewModel.getOptions(it)
        }
    }

    private fun setupCategoryDropDown() {
        context?.let {
            categoryAdapter = CategoryAdapter(it)
            binding.mainCategoriesDropDown.setAdapter(categoryAdapter)
            binding.mainCategoriesDropDown.setOnItemClickListener { parent, view, position, id ->
                val categories = parent.getItemAtPosition(position) as Categories
                if (categories.children != null && categories.children.isNotEmpty()) {
                    if (::suCategoryAdapter.isInitialized) {
                        binding.subCategoriesLayout.visibility = View.VISIBLE
                        suCategoryAdapter.setData(categories.children)
                    }
                } else {
                    binding.subCategoriesLayout.visibility = View.GONE
                }

                binding.mainCategoriesDropDown.setText(categories.name, false)

            }
        }
    }

    private fun setupSubCategoryDropDown() {
        context?.let {
            suCategoryAdapter = SubCategoryAdapter(it)
            binding.subCategoriesDropDown.setAdapter(suCategoryAdapter)
            binding.subCategoriesDropDown.setOnItemClickListener { parent, view, position, id ->
                val child = parent.getItemAtPosition(position) as Children
                viewModel.getSubCategories(child.id!!)
                binding.subCategoriesDropDown.setText(child.name, false)
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}