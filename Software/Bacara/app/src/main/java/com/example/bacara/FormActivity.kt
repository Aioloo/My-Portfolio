package com.example.bacara

import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class FormActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_profile)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val editNama = findViewById<EditText>(R.id.NamaLengkap)
        val editNickname = findViewById<EditText>(R.id.Nickname)
        val editTempatLahir = findViewById<EditText>(R.id.TempatLahir)
        val editTanggalLahir = findViewById<EditText>(R.id.TanggalLahir)
        val editNoTelp = findViewById<EditText>(R.id.NoTelp)
        val btnMasuk = findViewById<Button>(R.id.btnMasuk)
        val userId = intent.getStringExtra("userId")?: return

        btnMasuk.setOnClickListener {
            val nama = editNama.text.toString()
            val nickname = editNickname.text.toString()
            val tempatLahir = editTempatLahir.text.toString()
            val tanggalLahir = editTanggalLahir.text.toString()
            val noTelp = editNoTelp.text.toString()

            if(nama.isNotEmpty() && nickname.isNotEmpty() && tempatLahir.isNotEmpty() && tanggalLahir.isNotEmpty() && noTelp.isNotEmpty()){
                saveUserData(userId, nama, nickname, tempatLahir, tanggalLahir, noTelp)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Nickname", nickname)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserData(userId: String, nama: String, nickname: String, tempatLahir: String, tanggalLahir: String, noTelp: String) {
        val userMap = hashMapOf(
            "namaLengkap" to nama,
            "nickname" to nickname,
            "tempatLahir" to tempatLahir,
            "tanggalLahir" to tanggalLahir,
            "noTelp" to noTelp
        )
        db.collection("users").document(userId).set(userMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
            }
    }
}