package com.example.cland

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class forgotpassword : AppCompatActivity() {
    lateinit var firebaseAuth : FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        val editMail: EditText = findViewById(R.id.yourMail)
        val recover: Button = findViewById(R.id.recoverButton)
        val backToLoginText: TextView = findViewById(R.id.backtologinText)
        firebaseAuth = FirebaseAuth.getInstance()


        backToLoginText.setOnClickListener {
            val intent = Intent(this@forgotpassword, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        recover.setOnClickListener {
            val email = editMail.text.toString()
            if(email.isEmpty()){
                showToast("Enter your mail first")
            } else{
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        Toast.makeText(this,"please check your email",Toast.LENGTH_SHORT)

                    }
                    .addOnFailureListener{
                        Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT)

                    }
            }
        }
    }

    private fun showToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}