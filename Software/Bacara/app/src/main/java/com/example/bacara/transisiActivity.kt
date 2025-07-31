package com.example.bacara

import android.content.Intent
import android.widget.LinearLayout
import android.widget.ImageButton
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class transisiActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.transisi_materi)

        auth = FirebaseAuth.getInstance()

        val materi = intent.getStringExtra("materi")
        val quiz = intent.getStringExtra("quiz")
        val backButton = findViewById<ImageButton>(R.id.button_back)
        val cardMateri = findViewById<LinearLayout>(R.id.card_materi)
        val cardQuiz = findViewById<LinearLayout>(R.id.card_quiz)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cardMateri.setOnClickListener {
            when (materi){
                "huruf" -> startActivity(Intent(this, HurufChoice::class.java))
                "angka" -> startActivity(Intent(this, AngkaChoice::class.java))
                "warna" -> startActivity(Intent(this, WarnaChoice::class.java))
                "barang" -> startActivity(Intent(this, BarangChoice::class.java))
                else -> startActivity(Intent(this, MainActivity::class.java))
            }
        }

        cardQuiz.setOnClickListener{
            when (quiz){
                "huruf" -> startActivity(Intent(this, QuizHuruf::class.java))
                "angka" -> startActivity(Intent(this, QuizAngka::class.java))
                "warna" -> startActivity(Intent(this, QuizWarna::class.java))
                //"barang" -> startActivity(Intent(this, QuizBarang::class.java))
                else -> startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }
}