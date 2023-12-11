package com.example.geotag.data.holder

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import com.example.geotag.R
import androidx.recyclerview.widget.RecyclerView

class PartnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val partnerLogo: ImageView = itemView.findViewById(R.id.partnerpic_img)
    val partnerTitelTv: TextView = itemView.findViewById(R.id.tv_partnername)
}
