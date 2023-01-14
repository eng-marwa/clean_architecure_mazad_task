package com.marwa.mazadattask.presentation.details.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marwa.mazadattask.databinding.BidderRowBinding
import com.marwa.mazadattask.domain.entities.Bidder

class BiddersAdapter (private val context: Context): RecyclerView.Adapter<BiddersAdapter.BiddersVH>(){

    private val list = arrayListOf<Bidder>()
    inner class BiddersVH(itemView: BidderRowBinding) : RecyclerView.ViewHolder(itemView.root) {
        var lbBidderName = itemView.lbBidderName
        var lbBidderTime = itemView.lbBidderTime
        var ivBidder = itemView.ivBidder
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiddersVH = BiddersVH(
        BidderRowBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )
    override fun onBindViewHolder(holder: BiddersVH, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

}