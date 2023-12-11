package com.example.geotag.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R
import com.example.geotag.data.dummys.PartnersDummy
import com.example.geotag.data.holder.PartnerViewHolder

class PartnersAdapter(private var pList: List<PartnersDummy>) : RecyclerView.Adapter<PartnerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.patner_item_row, parent, false)
        return PartnerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        val (name, logo ) = pList[position]
        holder.partnerLogo.setImageResource(logo)
        holder.partnerTitelTv.text = name
    }
}