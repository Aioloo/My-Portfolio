package com.example.bacara

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContent)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nicknameText = findViewById<TextView>(R.id.nickameText)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val nickname = document.getString("nickname")
                        nicknameText.text = "Hallo, $nickname!"
                    }else {
                        nicknameText.text = "Hallo!, User"
                    }
                }
                    .addOnFailureListener { exception ->
                        nicknameText.text = "Datamu gaada"
                    }
                }

        val profil = findViewById<ImageView>(R.id.profilLogo)
        val angka = findViewById<LinearLayout>(R.id.materi_angka)
        val huruf = findViewById<LinearLayout>(R.id.materi_huruf)
        val warna = findViewById<LinearLayout>(R.id.materi_warna)
        val barang = findViewById<LinearLayout>(R.id.materi_barang)
        val daily = findViewById<Button>(R.id.button_daily)

        profil.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        huruf.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            intent.putExtra("materi", "huruf")
            intent.putExtra("quiz", "huruf")
            startActivity(intent)
        }

        angka.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            intent.putExtra("materi", "angka")
            intent.putExtra("quiz", "angka")
            startActivity(intent)
        }

        warna.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            intent.putExtra("materi", "warna")
            intent.putExtra("quiz", "warna")
            startActivity(intent)
        }

        barang.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            intent.putExtra("materi", "barang")
            intent.putExtra("quiz", "barang")
            startActivity(intent)
        }
    }
}