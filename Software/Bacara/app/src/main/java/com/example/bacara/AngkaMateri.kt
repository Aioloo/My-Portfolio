package com.example.bacara

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class AngkaMateri : AppCompatActivity() {

    private lateinit var angkaText: TextView
    private var index = 0
    private val angkaList = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,30,40,50,60)

    private val angkaKeHuruf = mapOf(
        1 to "satu",
        2 to "dua",
        3 to "tiga",
        4 to "empat",
        5 to "lima",
        6 to "enam",
        7 to "tujuh",
        8 to "delapan",
        9 to "sembilan",
        10 to "sepuluh",
        11 to "sebelas",
        12 to "dua belas",
        13 to "tiga belas",
        14 to "empat belas",
        15 to "lima belas",
        16 to "enam belas",
        17 to "tujuh belas",
        18 to "delapan belas",
        19 to "sembilan belas",
        20 to "dua puluh",
        30 to "tiga puluh",
        40 to "empat puluh",
        50 to "lima puluh",
        60 to "enam puluh"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.materi_angka)

        angkaText = findViewById(R.id.tulisan_angka)
        val backButton = findViewById<ImageButton>(R.id.backAngka)

        // Ambil angka dari intent
        val angkaAwal = intent.getIntExtra("ANGKA", 1)

        // Cari posisi index angka di dalam angkaList
        index = angkaList.indexOf(angkaAwal)

        updateAngka()

        findViewById<ImageButton>(R.id.btnNext).setOnClickListener {
            if (index < angkaList.size - 1) {
                index++
                updateAngka()
            }
        }

        findViewById<ImageButton>(R.id.btnPrev).setOnClickListener {
            if (index > 0) {
                index--
                updateAngka()
            }
        }

        backButton.setOnClickListener {
            val intent = Intent(this, AngkaChoice::class.java)
            startActivity(intent)
        }
    }

    private fun updateAngka() {
        val angka = angkaList[index]
        val hurufAngka = angkaKeHuruf[angka] ?: ""

        val angkaView = findViewById<TextView>(R.id.real_angka)
        val hurufView = findViewById<TextView>(R.id.tulisan_angka)

        angkaView.text = angka.toString()
        hurufView.text = hurufAngka
    }
}
