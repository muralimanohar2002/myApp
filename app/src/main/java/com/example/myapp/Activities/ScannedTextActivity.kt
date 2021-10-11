package com.example.myapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp.databinding.ActivityScannedTextBinding

class ScannedTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScannedTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannedTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cross.setOnClickListener {
            super.onBackPressed()
        }
        binding.scText.text = intent.extras?.get("sctext").toString()
    }
}