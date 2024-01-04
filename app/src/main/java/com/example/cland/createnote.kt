package com.example.cland

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class createnote : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createnote)
        lateinit var firebaseAuth: FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        lateinit var firebaseFirestore: FirebaseFirestore
        firebaseFirestore = FirebaseFirestore.getInstance()
        lateinit var firebaseUser: FirebaseUser


        val editText : EditText = findViewById(R.id.createtitleofnote)
        val meditText : EditText = findViewById(R.id.createcontentofnote)
        val floatButton: Button = findViewById(R.id.savenote)
        val tool : Toolbar = findViewById(R.id.toolbarofcreatenote)

        setSupportActionBar(tool)
        getSupportActionBar()?.setDefaultDisplayHomeAsUpEnabled(true)

        floatButton.setOnClickListener {
            val title = editText.getText().toString()
            val content = meditText.getText().toString()

            if(title.isEmpty() || content.isEmpty())
            {
                showToast("All fields are required")
            } else
            {
                val documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("myNotes").document()
                val data = hashMapOf<String,Object>(



                )
                firebaseFirestore.collection("users")
                    .add(data)
                    .addOnSuccessListener {
                        nowToast("note created")
                        val intent = Intent(this@createnote, notesactivity::class.java)
                        startActivity(intent)
                        finish()

                    } .addOnFailureListener {
                        yoToast("failed ")
                    }
            }
        }

    }

    private fun yoToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun nowToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.getItemId()== android.R.id.home)
        {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}