package com.example.myapp.Activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.databinding.ActivityOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.loaderLayout.visibility = View.VISIBLE

        //Firebase auth instance
        fAuth = FirebaseAuth.getInstance()
        val number = intent.extras?.get("number").toString()
        Log.d("mm", number.toString())
        val options = PhoneAuthOptions.newBuilder(fAuth)
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    binding.progressBar.loaderLayout.visibility = View.GONE
                    binding.verifyOtpTxt.setOnClickListener {
                        binding.progressBar.loaderLayout.visibility = View.VISIBLE
                        signInWithPhoneAuthCredential(credential)
                        Log.d("mm", "verify")
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    binding.progressBar.loaderLayout.visibility = View.GONE
                    Log.w("mm", "onVerificationFailed", p0)

                    if (p0 is FirebaseAuthInvalidCredentialsException) {
                        Log.d("mm", "FirebaseAuthInvalidCredentialsException")
                    } else if (p0 is FirebaseTooManyRequestsException) {
                        Log.d("mm", "FirebaseTooManyRequestsException")
                    }
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    binding.progressBar.loaderLayout.visibility = View.GONE
                    Log.d("mm", "verifyocs")
                }
            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        fAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("mm", "signInWithCredential:success")
                    val intent = Intent(this, ProfileNameActivity::class.java)
                    startActivity(intent)
                } else {
                   Toast.makeText(this, "Verification failed", Toast.LENGTH_LONG).show()
                }
            }
    }
}