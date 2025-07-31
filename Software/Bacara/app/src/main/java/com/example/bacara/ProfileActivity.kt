package com.example.bacara

import android.content.Intent
import android.widget.TextView
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val userId = auth.currentUser?.uid ?: return

        val namaView = findViewById<TextView>(R.id.NamaLengkap)
        val tempatLahirView = findViewById<TextView>(R.id.TempatLahir)
        val tanggalLahirView = findViewById<TextView>(R.id.TanggalLahir)
        val emailView = findViewById<TextView>(R.id.EmailUser)
        val noTelpView = findViewById<TextView>(R.id.NoTelp)
        val nicknameView = findViewById<TextView>(R.id.Nickname)
        val backMenu = findViewById<TextView>(R.id.backMenu)

        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    namaView.text = "Nama: " + document.getString("namaLengkap")
                    tempatLahirView.text = "Tempat Lahir: " + document.getString("tempatLahir")
                    tanggalLahirView.text = "Tanggal Lahir: " + document.getString("tanggalLahir")
                    emailView.text = "Email: " + auth.currentUser?.email
                    noTelpView.text = "No Telp: " + document.getString("noTelp")
                    nicknameView.text = document.getString("nickname")
                } else {
                    Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }

            .addOnFailureListener { exception ->
                Toast.makeText(this, "Gagal mengambil data: ${exception.message}", Toast.LENGTH_SHORT).show()
            }

        backMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }
    }
}