package com.example.myapp.Fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp.R
import com.example.myapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var fAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        childFragmentManager.beginTransaction().apply {
            replace(R.id.nw_fragContainer, NewsFragment())
            commit()
        }

        newsToWeatherTabChange()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun newsToWeatherTabChange() {
        binding.weather.setOnClickListener {
            binding.weather.setBackgroundResource(R.drawable.bg_blue)
            binding.weather.setTextColor(resources.getColor(R.color.white))
            binding.news.setBackgroundResource(0)
            binding.news.setTextColor(resources.getColor(R.color.black))

            childFragmentManager.beginTransaction().apply {
                replace(R.id.nw_fragContainer, WeatherFragment())
                commit()
            }
        }
        binding.news.setOnClickListener {
            binding.news.setBackgroundResource(R.drawable.bg_blue)
            binding.news.setTextColor(resources.getColor(R.color.white))
            binding.weather.setBackgroundResource(0)
            binding.weather.setTextColor(resources.getColor(R.color.black))

            childFragmentManager.beginTransaction().apply {
                replace(R.id.nw_fragContainer, NewsFragment())
                commit()
            }
        }
    }

}