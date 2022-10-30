package com.example.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = FirebaseAuth.getInstance()
        signup.setOnClickListener {
            val email = emailup.text.toString()
            val pass = passup.text.toString()
            val confirmPass = passupcon.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "Passwords is not the same!", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_SHORT).show()
            }
        }
        returnback.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}