package com.example.myapp.Fragments.qrcode

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapp.Activities.QRImageActivity
import com.example.myapp.databinding.FragmentMakerBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MakerFragment : Fragment() {
    private lateinit var binding: FragmentMakerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.makeQrBtn.setOnClickListener {
            val data = binding.qrText.text.toString().trim()
            if(data.isEmpty()){
                Toast.makeText(requireContext(), "Please enter text", Toast.LENGTH_LONG).show()
            } else {
                val writer = QRCodeWriter()
                try {
                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height

                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for(x in 0 until width){
                        for(y in 0 until height){
                            bmp.setPixel(x, y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    val intent = Intent(requireContext(), QRImageActivity::class.java)
                    intent.putExtra("imgQr", bmp)
                    intent.putExtra("textQr", data)
                    startActivity(intent)

                } catch (e: WriterException){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakerBinding.inflate(inflater, container, false)
        return binding.root
    }

}