package com.example.myapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapp.Fragments.home.HomeFragment
import com.example.myapp.Fragments.profile.ProfileFragment
import com.example.myapp.Fragments.qrcode.QrCodeFragment
import com.example.myapp.R
import com.example.myapp.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    private val homeFrag = HomeFragment()
    private val qrFrag = QrCodeFragment()
    private val profileFrag = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFrag)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFrag)
                R.id.qr_code -> replaceFragment(qrFrag)
                R.id.profile -> replaceFragment(profileFrag)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}