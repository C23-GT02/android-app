package com.example.geotag.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geotag.ui.profile.ProfileActivity
import com.example.geotag.R
import com.example.geotag.data.adapter.PartnersAdapter
import com.example.geotag.data.adapter.ProductAdapter
import com.example.geotag.data.data2.PartnersData
import com.example.geotag.data.data2.ProductData
import com.example.geotag.ui.history.HistoryActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var botNavView: BottomNavigationView
    //for_partners
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var partnersAdapter: PartnersAdapter
    //for_product
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horizontalScrollView = findViewById<HorizontalScrollView>(R.id.hz_scrollview)

        // Create dummy data partners
        val partnersDataList = PartnersData.partnersDataList()
        partnersAdapter = PartnersAdapter(partnersDataList)

        //for product
        recyclerView = findViewById(R.id.vt_recyclerview)
        val numberOfColumns = 2
        val layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.layoutManager = layoutManager
        productAdapter = ProductAdapter(ProductData.productDataList())
        recyclerView.adapter = productAdapter


        botBarBind()
        botNavView.selectedItemId = R.id.bottom_home
        botBarHandle()
        setupHorizontalScrollView()
    }

    private fun setupHorizontalScrollView() {
        val linearLayout = findViewById<LinearLayout>(R.id.hz_linearlayout)

        // Create dummy data
        val partnersDataList = PartnersData.partnersDataList()
        partnersAdapter = PartnersAdapter(partnersDataList)

        // Create a LinearLayout to host the HorizontalScrollView content
        val horizontalLayout = LinearLayout(this)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.addView(horizontalLayout)

        for (partner in partnersDataList) {
            // Inflate the view for each partner and add it to the LinearLayout
            val partnerView = LayoutInflater.from(this).inflate(R.layout.patner_item_row, horizontalLayout, false)
            val partnerLogo = partnerView.findViewById<ImageView>(R.id.partnerpic_img)
            val partnerTitle = partnerView.findViewById<TextView>(R.id.tv_partnername)

            // Set the data for each partner
            partnerLogo.setImageResource(partner.partnersLogo)
            partnerTitle.text = partner.partnersTitle

            horizontalLayout.addView(partnerView)
        }
    }

    private fun botBarHandle() {
        botNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> true
                R.id.bottom_history -> {
                    loadActivities(HistoryActivity())
                    true
                }
                R.id.bottom_profile -> {
                    loadActivities(ProfileActivity())
                    true
                }
                else -> false
            }
        }
    }

    private fun botBarBind() {
        botNavView = findViewById(R.id.bottomNav)
    }

    private fun loadActivities(activity: AppCompatActivity) {
        startActivity(Intent(applicationContext, activity::class.java))
        finish()
    }
}

//un-used

//    private fun botBarHandle() {
//        botNavView.setOnItemSelectedListener{
//            when (it.itemId) {
//                R.id.bottom_home -> {
//                    return@setOnItemSelectedListener true
//                }
//
////                R.id.bottom_scan -> {
////                    loadActivities(())
////                    return@setOnItemSelectedListener true
////                }
//
//                R.id.bottom_history -> {
//                    loadActivities(HistoryActivity())
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.bottom_profile -> {
//                    loadActivities(ProfileActivity())
//                    return@setOnItemSelectedListener true
//                }
//            }
//            return@setOnItemSelectedListener false
//        }
//    }

//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)