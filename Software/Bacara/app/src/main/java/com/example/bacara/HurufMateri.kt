package com.example.bacara

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class HurufMateri : AppCompatActivity() {

    private lateinit var hurufBesar: TextView
    private lateinit var hurufKecil: TextView
    private var index = 0

    private val hurufList = ('A'..'Z').toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.materi_huruf)

        hurufBesar = findViewById(R.id.hurufBesar)
        hurufKecil = findViewById(R.id.hurufKecil)
        val backButton = findViewById<ImageButton>(R.id.backHuruf)

        backButton.setOnClickListener {
            val intent = Intent (this, HurufChoice::class.java)
            startActivity(intent)
        }

        val hurufAwal = intent.getStringExtra("HURUF") ?: "A"

        index = hurufList.indexOf(hurufAwal[0])

        updateHuruf()

        findViewById<ImageButton>(R.id.btnNext).setOnClickListener {
            if (index < hurufList.size - 1) {
                index++
                updateHuruf()
            }
        }

        findViewById<ImageButton>(R.id.btnPrev).setOnClickListener {
            if (index > 0) {
                index--
                updateHuruf()
            }
        }
    }

    private fun updateHuruf() {
        val huruf = hurufList[index]
        hurufBesar.text = huruf.toString()
        hurufKecil.text = huruf.lowercase()
    }
}
