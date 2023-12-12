package com.example.geotag.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geotag.ui.profile.ProfileActivity
import com.example.geotag.R
import com.example.geotag.data.adapter.PartnersAdapter
import com.example.geotag.data.adapter.ProductAdapter
import com.example.geotag.data.response.HomeResponse
import com.example.geotag.data.response.PartnerCollectionItem
import com.example.geotag.data.retrofit.ApiService
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import com.example.geotag.ui.history.HistoryActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    //for_partners
    private lateinit var partnerScrollView: HorizontalScrollView
    //for_product
    private lateinit var recyclerView: RecyclerView
    private lateinit var botNavView: BottomNavigationView
    private lateinit var productAdapter: ProductAdapter
//    private lateinit var partnerAdapter: PartnersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        //for partner
        partnerScrollView = findViewById(R.id.hz_scrollview)
        //for product
        recyclerView = findViewById(R.id.vt_recyclerview)

        val numberOfColumns = 2
        val layoutManager = GridLayoutManager(this, numberOfColumns)
        recyclerView.layoutManager = layoutManager


//        fetchProducts(apiService)
        fetchPartners(apiService)
        botBarBind()
        botNavView.selectedItemId = R.id.bottom_home
        botBarHandle()
    }

    //for partner
    private fun fetchPartners(apiService: ApiService) {
        val call: Call<HomeResponse> = apiService.getHome()

        fetch(call,
            success = { homeResponse, _ ->
                val partnerCollection = homeResponse?.partnerCollection
                val productCollection = homeResponse?.productCollection
                if (partnerCollection != null && productCollection != null ) {
                    // Display partners in HorizontalScrollView
                    setupHorizontalScrollView(partnerCollection)
                    productAdapter = ProductAdapter(productCollection)
                    recyclerView.adapter = productAdapter
                }
            },
            error = { code, message ->
                // Handle error
                showToast("Failed to fetch partners: $code $message")
            }
        )
    }

    private fun setupHorizontalScrollView(partnerCollection: List<PartnerCollectionItem>) {
        val linearLayout = findViewById<LinearLayout>(R.id.hz_linearlayout)

        for (partner in partnerCollection) {
            val partnerView = LayoutInflater.from(this).inflate(R.layout.patner_item_row, null)
            val partnerLogo = partnerView.findViewById<ImageView>(R.id.partnerpic_img)
            val partnerTitelTv = partnerView.findViewById<TextView>(R.id.tv_partnername)

            // Load image using Glide or Picasso, assuming partner.logo is a String URL
            Glide.with(this)
                .load(partner.logo)
                .into(partnerLogo)

            partnerTitelTv.text = partner.displayName

            // Add the partner view to the linear layout
            linearLayout.addView(partnerView)
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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun botBarBind() {
        botNavView = findViewById(R.id.bottomNav)
    }

    private fun loadActivities(activity: AppCompatActivity) {
        startActivity(Intent(applicationContext, activity::class.java))
        finish()
    }
}

//    private lateinit var partnerAdapter: PartnersAdapter

//    private fun setupHorizontalScrollView() {
//        val linearLayout = findViewById<LinearLayout>(R.id.hz_linearlayout)
//        // Create a LinearLayout to host the HorizontalScrollView content
//        val horizontalLayout = LinearLayout(this)
//        horizontalLayout.orientation = LinearLayout.HORIZONTAL
//        linearLayout.addView(horizontalLayout)
//    }

//for paroduct
//    private fun fetchProducts(apiService: ApiService) {
//        // Replace "your_partner_name" with the actual partner name
////        val partnerName = "your_partner_name"
//        val call: Call<HomeResponse> = apiService.getHome()
//
//        fetch(call,
//            success = { productResponse, _ ->
//                val productCollection = productResponse?.productCollection
//                if (productCollection != null) {
//                    // Display products in RecyclerView
//                    productAdapter = ProductAdapter(productCollection)
//                    recyclerView.adapter = productAdapter
//                }
//            },
//            error = { code, message ->
//                // Handle error
//                showToast("Failed to fetch products: $code $message")
//            }
//        )
//    }

//un-used
//new
//private lateinit var partnersAdapter: PartnersAdapter
//private lateinit var productAdapter: ProductAdapter
//// Create dummy data partners
//val partnersDataList = PartnersData.partnersDataList()
//partnersAdapter = PartnersAdapter(partnersDataList)
//productAdapter = ProductAdapter(ProductData.productDataList())
//
//productAdapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {
//    override fun onItemClick(position: Int) {
//        // Get the selected product
//        val selectedProduct = ProductData.productDataList()[position]
//
//        // Navigate to DetailProductActivity with the selected product
//        val intent = Intent(this@MainActivity, ProductDetailsActivity::class.java)
//        intent.putExtra("product", selectedProduct)
//        startActivity(intent)
//    }
//})
//
//recyclerView.adapter = productAdapter
// Create dummy data
//val partnersDataList = PartnersData.partnersDataList()
//partnersAdapter = PartnersAdapter(partnersDataList)
//for (partner in partnersDataList) {
//    // Inflate the view for each partner and add it to the LinearLayout
//    val partnerView = LayoutInflater.from(this).inflate(R.layout.patner_item_row, horizontalLayout, false)
//    val partnerLogo = partnerView.findViewById<ImageView>(R.id.partnerpic_img)
//    val partnerTitle = partnerView.findViewById<TextView>(R.id.tv_partnername)
//
//    // Set the data for each partner
//    partnerLogo.setImageResource(partner.partnersLogo)
//    partnerTitle.text = partner.partnersTitle
//
//    horizontalLayout.addView(partnerView)
//}


//old
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