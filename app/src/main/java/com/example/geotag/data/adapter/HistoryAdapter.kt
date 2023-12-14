package com.example.geotag.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.geotag.R
import com.example.geotag.data.holder.HistoryViewHolder
import com.example.geotag.data.response.ResponseHistoryItem

class HistoryAdapter(private val context: Context, initialHistoryList: List<ResponseHistoryItem>) : RecyclerView.Adapter<HistoryViewHolder>() {

    private val historyList: List<ResponseHistoryItem> = initialHistoryList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_row, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(currentItem.productRef)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.pictureImageView)
        holder.nameTextView.text = currentItem.name
        holder.valueDateTextView.text = currentItem.timestamp
    }
}