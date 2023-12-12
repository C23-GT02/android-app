package com.example.geotag.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R
import com.example.geotag.data.dummys.ProductDummy
import com.example.geotag.data.holder.ProductViewHolder

class ProductAdapter(private var productList: List<ProductDummy>) : RecyclerView.Adapter<ProductViewHolder> () {
    private var onItemClickListener: OnItemClickListener? = null

    // Interface for item click events
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_item_row, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val (pName, pDesc, pLogo ) = productList[position]
        holder.productPict.setImageResource(pLogo)
        holder.productName.text = pName
        holder.productDesc.text = pDesc

        // Set click listener for the entire item view
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

}