package com.example.cland

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cland.databinding.ActivitySignupBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.auth.auth

class signup : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = Firebase.auth

        val editText1 : EditText = findViewById(R.id.email)
        val editText2 : EditText = findViewById(R.id.password)
        val text : TextView = findViewById(R.id.textLogin)
        val createAccountButton : Button = findViewById(R.id.accountbutton)








        createAccountButton.setOnClickListener {
            val email = editText1.text.toString().trim()
            val password = editText2.text.toString().trim()
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user= firebaseAuth.currentUser

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
        val intent = Intent(this@signup, MainActivity::class.java)
        startActivity(intent)
        finish()

    }


}



















