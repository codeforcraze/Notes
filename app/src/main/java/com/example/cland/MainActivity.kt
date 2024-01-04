package com.example.cland

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cland.databinding.ActivityMainBinding
import com.example.cland.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        firebaseAuth = Firebase.auth


        val emailEditText: EditText = findViewById(R.id.emailEdit)
        val passwordEditText: EditText = findViewById(R.id.passwordEdit)
        val loginButton: Button = findViewById(R.id.loginButton)
        val newUserButton: Button = findViewById(R.id.newUserButton)
        val forgotText: TextView = findViewById(R.id.forgotText)

        forgotText.setOnClickListener {
            val intent = Intent(this@MainActivity, forgotpassword::class.java)
            startActivity(intent)
            finish()
        }

        newUserButton.setOnClickListener {
            val intent = Intent(this@MainActivity, signup::class.java)
            startActivity(intent)
            finish()
        }
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = firebaseAuth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        updateUI(null)
                    }
                }


        }
    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this@MainActivity, notesactivity::class.java)
        startActivity(intent)
        finish()
    }


}
