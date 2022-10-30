package com.example.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.auth.signup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gotologup.setOnClickListener {
            val intent = Intent(this , signup::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        login.setOnClickListener {
            val email = emailin.text.toString()
            val pass = passin.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() ){
                    firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, TheApp::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}