package com.example.geotag.data.data2

import com.example.geotag.R
import com.example.geotag.data.dummys.PartnersDummy

object PartnersData {
    fun partnersDataList(): ArrayList<PartnersDummy>{
        val pList = ArrayList<PartnersDummy>()

        pList.add(PartnersDummy("Homa Herbs", R.drawable.homaherbico))
        pList.add(PartnersDummy("Homa Herbs", R.drawable.homaherbico))
        pList.add(PartnersDummy("Homa Herbs", R.drawable.homaherbico))
        pList.add(PartnersDummy("Homa Herbs", R.drawable.homaherbico))
        pList.add(PartnersDummy("Homa Herbs", R.drawable.homaherbico))

        return pList
    }
}