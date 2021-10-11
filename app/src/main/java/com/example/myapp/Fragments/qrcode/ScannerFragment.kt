package com.example.myapp.Fragments.qrcode

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp.Activities.CodeScannerActivity
import com.example.myapp.databinding.FragmentScannerBinding

class ScannerFragment : Fragment() {
    private lateinit var binding: FragmentScannerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openCamera.setOnClickListener{
            startActivity(Intent(requireContext(), CodeScannerActivity::class.java))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

}