package com.example.geotag.ui.scan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.geotag.data.models.QrPayload
import com.example.geotag.databinding.ActivityScanBinding
import com.example.geotag.ui.main.MainActivity
import com.example.geotag.ui.scandetail.ScanDetailActivity
import com.example.geotag.ui.welcome.WelcomeActivity
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabScan.setOnClickListener {
            requestPermissionCamera(this)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted: Boolean ->
        if (isGranted){
            showCamera()
        }
        else{
            //explain why we need access
        }
    }

    private val scanLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        run {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val jsonData = Gson().fromJson(result.contents, QrPayload::class.java)

                    // Check if the scanned data matches the structure of the QrPayload data class
                    if (jsonData is QrPayload) {
                        navigateToScanDetail(jsonData)
                    } else {
                        // If not matching, continue scanning or handle as needed
                        Toast.makeText(this, "Scanned data doesn't match the expected format", Toast.LENGTH_SHORT).show()
                        // Optionally, initiate another scan
                        // startScan()
                    }
                } catch (e: JsonSyntaxException) {
                    Toast.makeText(this, "Invalid JSON format", Toast.LENGTH_SHORT).show()
                    // Optionally, handle the case where JSON parsing fails
                }
            }
        }
    }


    private fun requestPermissionCamera(context: Context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            showCamera()
        }
        else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
            Toast.makeText(context, "CAMERA permission required", Toast.LENGTH_SHORT).show()
        }
        else{
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan QR code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)

        scanLauncher.launch(options)
    }

    private fun navigateToScanDetail(scannedResult: QrPayload) {
        val intent = Intent(this, ScanDetailActivity::class.java)

        // Pass the scanned result as an extra to the intent
        intent.putExtra("QrPayload", scannedResult)
        // Start the ScanDetailActivity with the intent
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Handle the back button press
        navigateBackToHomeActivity()
    }

    private fun navigateBackToHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to remove it from the stack
    }
}

//
//    private fun setResult(string: String){
//        binding.textResult.text = string
//    }