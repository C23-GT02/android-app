package com.example.geotag.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R
import com.example.geotag.data.diffcallback.PartnersDiffCallback
import com.example.geotag.data.holder.PartnerViewHolder
import com.example.geotag.data.response.PartnerCollectionItem

class PartnersAdapter(private var partnerList: List<PartnerCollectionItem>) : RecyclerView.Adapter<PartnerViewHolder>() {

    fun updateData(newPartnerList: List<PartnerCollectionItem>) {
        // Calculate the differences between the old and new data
        val diffResult = DiffUtil.calculateDiff(PartnersDiffCallback(partnerList, newPartnerList))
        // Update the dataset
        partnerList = newPartnerList
        // Dispatch the specific change events to the adapter
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.patner_item_row, parent, false)
        return PartnerViewHolder(view)
    }
    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        val partnerItem = partnerList[position]
        holder.bind(partnerItem)
    }
    override fun getItemCount(): Int {
        return partnerList.size
    }
}