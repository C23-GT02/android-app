package com.example.geotag.data.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.geotag.R
import com.example.geotag.data.response.Product

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val productPict: ImageView = itemView.findViewById(R.id.productpic_img)
    private val productName: TextView = itemView.findViewById(R.id.tv_productname)
    private val productDesc: TextView = itemView.findViewById(R.id.tv_productdesc)

    fun bind(product: Product) {
        // Load and display the product image using Glide
        Glide.with(itemView.context)
            .load(product.images.firstOrNull()) // Use the first image URL if available
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.loadingpic) // Placeholder image while loading
            .error(R.drawable.errorpic) // Image to display in case of error
            .into(productPict)

        productName.text = product.name
        productDesc.text = product.deskripsi

        itemView.setOnClickListener {
            // Handle click on the product item if needed
        }
    }
}