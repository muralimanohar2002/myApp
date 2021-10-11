package com.example.myapp.Fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapp.Activities.MainActivity
import com.example.myapp.R
import com.example.myapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var fAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fAuth = FirebaseAuth.getInstance()
        onSetup()
    }

    private fun onSetup() {
        binding.editProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Coming Soon!", Toast.LENGTH_LONG).show()
        }
        binding.logout.setOnClickListener {
            fAuth.signOut()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

}