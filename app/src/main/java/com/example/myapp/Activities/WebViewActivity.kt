package com.example.myapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.example.myapp.R
import com.example.myapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSetup()
    }

    private fun onSetup() {
        val newsUrl = intent.extras?.get("newsUrl").toString()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(newsUrl)
        val webSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if(binding.webView.canGoBack())
        {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}