package com.example.myapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapp.Models.Users
import com.example.myapp.databinding.ActivityProfileNameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileNameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileNameBinding
    private lateinit var fAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.next.setOnClickListener {
            binding.progressBar.loaderLayout.visibility = View.VISIBLE
            if(binding.nameTxt.text?.isEmpty() == true){
                binding.progressBar.loaderLayout.visibility = View.GONE
                Toast.makeText(this, "Enter your name", Toast.LENGTH_LONG).show()
            } else {
                val name = binding.nameTxt.text.toString()
                val phoneNum = fAuth.currentUser?.phoneNumber.toString()
                val uid = fAuth.uid.toString()

                val user = Users(uid, name, phoneNum)

                database.reference.child("Users").child(uid).setValue(user).addOnSuccessListener {
                    val intent = Intent(this, DashBoardActivity::class.java)
                    binding.progressBar.loaderLayout.visibility = View.GONE
                    intent.putExtra("mCode", 1)
                    startActivity(intent)
                    finishAffinity()
                }
            }
        }
    }
}