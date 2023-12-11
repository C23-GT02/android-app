package com.example.geotag.data.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productPict: ImageView = itemView.findViewById(R.id.productpic_img)
    val productName: TextView = itemView.findViewById(R.id.tv_productname)
    val productDesc: TextView = itemView.findViewById(R.id.tv_productdesc)
}