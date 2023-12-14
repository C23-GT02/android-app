package com.example.geotag.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.geotag.R
import com.example.geotag.data.holder.HistoryViewHolder
import com.example.geotag.data.response.ResponseHistoryItem

class HistoryAdapter(private val context: Context, private val historyList: List<ResponseHistoryItem>) :
    RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.history_row, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]

        // Load image using Glide
        Glide.with(context)
            .load(currentItem.productRef)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.pictureImageView)

        holder.nameTextView.text = currentItem.name
        holder.valueDateTextView.text = currentItem.timestamp
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}