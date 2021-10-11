package com.example.myapp.Activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp.databinding.ActivityQrimageBinding

class QRImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQrimageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrimageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val qrText = intent.extras?.get("textQr")
        binding.textToConvert.text = qrText.toString()

        val imgQr = intent.extras?.get("imgQr") as Bitmap
        binding.qrCodeImage.setImageBitmap(imgQr)

        binding.cross.setOnClickListener {
            super.onBackPressed()
        }
    }


}