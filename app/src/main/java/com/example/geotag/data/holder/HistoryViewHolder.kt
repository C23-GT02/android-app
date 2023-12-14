package com.example.geotag.data.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.R

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val pictureImageView: ImageView = itemView.findViewById(R.id.picture_img)
    val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
    val valueDateTextView: TextView = itemView.findViewById(R.id.tv_val_date)
}