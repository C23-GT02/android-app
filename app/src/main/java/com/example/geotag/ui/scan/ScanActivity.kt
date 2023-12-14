package com.example.geotag.ui.scan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.geotag.databinding.ActivityScanBinding
import com.example.geotag.ui.scandetail.ScanDetailActivity
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

    private val scanLauncher = registerForActivityResult(ScanContract()){ result: ScanIntentResult ->
        run {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
//                setResult(result.contents)
                navigateToScanDetail(result.contents)
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

    private fun navigateToScanDetail(scannedResult: String) {
        val intent = Intent(this, ScanDetailActivity::class.java)
        intent.putExtra("SCAN_RESULT", scannedResult)
        startActivity(intent)
    }

    private fun setResult(string: String){
        binding.textResult.text = string
    }
}