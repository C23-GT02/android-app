package com.example.geotag.data.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.geotag.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.geotag.data.response.PartnerCollectionItem

class PartnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val partnerLogo: ImageView = itemView.findViewById(R.id.partnerpic_img)
    private val partnerTitleTv: TextView = itemView.findViewById(R.id.tv_partnername)

    fun bind(partners: PartnerCollectionItem){
        Glide.with(itemView.context)
            .load(partners.logo)
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.loadingpic) // Placeholder image while loading
            .error(R.drawable.errorpic) // Image to display in case of error
            .into(partnerLogo)

        partnerTitleTv.text = partners.displayName

        itemView.setOnClickListener {
            // Handle click on the product item if needed
        }
    }
}
