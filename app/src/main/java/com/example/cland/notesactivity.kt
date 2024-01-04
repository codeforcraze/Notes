package com.example.cland

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class notesactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notesactivity)
        lateinit var firebaseAuth: FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        val recyclerView : RecyclerView
        val staggered : StaggeredGridLayoutManager
        var firebaseUser : FirebaseUser
        var firebaseFirestore : FirebaseFirestore

        val floatButton: Button = findViewById(R.id.createnotefab)
        supportActionBar?.title = "All Notes"

        floatButton.setOnClickListener {
            val intent = Intent(this@notesactivity, createnote::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.logout -> {

                lateinit var firebaseAuth: FirebaseAuth
                firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signOut()
                finish()
                val intent = Intent(this@notesactivity, MainActivity::class.java)
                startActivity(intent)
                finish()

                true
            }

            else -> super.onOptionsItemSelected(item)

        }

    }
}