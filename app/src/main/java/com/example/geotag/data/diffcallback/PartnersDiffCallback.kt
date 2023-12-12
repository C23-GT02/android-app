package com.example.geotag.data.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.example.geotag.data.response.PartnerCollectionItem

class PartnersDiffCallback(
    private val oldList: List<PartnerCollectionItem>,
    private val newList: List<PartnerCollectionItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}