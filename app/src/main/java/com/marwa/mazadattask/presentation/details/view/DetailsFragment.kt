package com.marwa.mazadattask.presentation.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwa.mazadattask.R
import com.marwa.mazadattask.databinding.FragmentDetailsBinding
import com.marwa.mazadattask.presentation.details.adapters.BiddersAdapter
import com.marwa.mazadattask.presentation.details.adapters.ItemsAdapter

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupBiddersRV()
        setupItemsRV()
    }

    private fun setupItemsRV() {
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
            binding.similarItems.rvSimilarItems.layoutManager =
                linearLayoutManager
            binding.similarItems.rvSimilarItems.addItemDecoration(
                DividerItemDecoration(
                    context,
                    linearLayoutManager.orientation
                )
            )
            val iAdapter = ItemsAdapter(context)
            binding.similarItems.rvSimilarItems.adapter = iAdapter

        }
    }

    private fun setupBiddersRV() {
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context)
            binding.bidderLayout.rvBidders.layoutManager =
                linearLayoutManager
            val bAdapter = BiddersAdapter(context)
            binding.bidderLayout.rvBidders.adapter = bAdapter

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}