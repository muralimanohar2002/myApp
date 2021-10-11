package com.example.myapp.Fragments.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp.R
import com.example.myapp.databinding.FragmentQrCodeBinding

class QrCodeFragment : Fragment() {
    private lateinit var binding: FragmentQrCodeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction().apply {
            replace(R.id.sm_fragContainer, ScannerFragment())
            commit()
        }
        scannerToMakerTabChange()
    }

    private fun scannerToMakerTabChange() {
        binding.maker.setOnClickListener {
            binding.maker.setBackgroundResource(R.drawable.bg_blue)
            binding.maker.setTextColor(resources.getColor(R.color.white))
            binding.scanner.setBackgroundResource(0)
            binding.scanner.setTextColor(resources.getColor(R.color.black))

            childFragmentManager.beginTransaction().apply {
                replace(R.id.sm_fragContainer, MakerFragment())
                commit()
            }
        }
        binding.scanner.setOnClickListener {
            binding.scanner.setBackgroundResource(R.drawable.bg_blue)
            binding.scanner.setTextColor(resources.getColor(R.color.white))
            binding.maker.setBackgroundResource(0)
            binding.maker.setTextColor(resources.getColor(R.color.black))

            childFragmentManager.beginTransaction().apply {
                replace(R.id.sm_fragContainer, ScannerFragment())
                commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQrCodeBinding.inflate(inflater, container, false)
        return binding.root
    }
}