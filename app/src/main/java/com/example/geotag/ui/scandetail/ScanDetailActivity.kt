package com.example.geotag.ui.scandetail
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.geotag.R
import com.example.geotag.data.models.QrPayload
import com.example.geotag.data.models.UserDataProfile
import com.example.geotag.data.response.Product
import com.example.geotag.data.retrofit.apiService
import com.example.geotag.data.retrofit.fetch
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScanDetailActivity : AppCompatActivity() {

    private var qrPayload: QrPayload? = null
    private lateinit var idTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var timeStampTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imageProduct: ImageView


    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view first
        setContentView(R.layout.activity_scan_detail)

        // Find the TextView after setting the content view
        idTextView = findViewById(R.id.uid_value_tv)
        nameTextView = findViewById(R.id.owner_name_value_tv)
        productNameTextView = findViewById(R.id.product_name_value_tv)
        timeStampTextView = findViewById(R.id.scan_date_value_tv)
        descriptionTextView = findViewById(R.id.description_value_tv)
        imageProduct = findViewById(R.id.shapeableImageView)

        // Retrieve the QrPayload from the intent
        qrPayload = intent.getParcelableExtra("QrPayload")

        // Check if qrPayload is empty
        if (qrPayload == null) {
            showToast("QR code is empty")
            // Optionally, you can finish() the activity or handle it as needed
            // finish()
        } else {
            // Display the non-empty QR code data
            idTextView.text = qrPayload!!.id
            productNameTextView.text = qrPayload!!.name
            timeStampTextView.text = getCurrentDateAndTime()
            showToast("QR code: ${qrPayload?.toString()}")
            // Make the API call asynchronously
             makeApiCall(qrPayload!!.productRef)
        }
    }

    private fun makeApiCall(productRef: String) {
        val sharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")

        val fetchProduct: Call<Product> = apiService.scanQr(productRef)
        val fetchUser: Call<UserDataProfile> = apiService.getUserData(email!!)
        fetch(
            fetchProduct,
            success = { product, _ ->
                // Handle the success case
                descriptionTextView.text = product?.deskripsi.toString()
                val imageURL = product?.images.toString()

                Glide.with(this).load(imageURL).into(imageProduct)
                // You can update UI or perform additional actions based on the API response
            },
            error = { code, message ->
                // Handle the error case
                showToast("API call failed: $message")
                // You can display an error message or take appropriate action
            }
        )

        fetch(
            fetchUser,
            success = { userData, _ ->
                // Handle the success case
                nameTextView.text = userData?.displayName.toString()
                showToast("berhasil fetch user")
            },
            error = { code, message ->
                // Handle the error case
                showToast("API call failed: $message")
                // You can display an error message or take appropriate action
            }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCurrentDateAndTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDateAndTime = Date()
        return dateFormat.format(currentDateAndTime)
    }
}
