package com.example.bacara

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class BarangMateri : AppCompatActivity(){

    private lateinit var hurufBarang : TextView
    private lateinit var namaBarang : TextView
    private lateinit var gambarBarang : ImageView
    private var index = 0
    private val hurufList = ('A'..'Z').toList()

    private val dataHuruf = mapOf(
        'A' to Pair("Amplop", R.drawable.amplop),
        'B' to Pair("Buku", R.drawable.buku),
        'C' to Pair("Celana", R.drawable.celana),
        'D' to Pair("Dompet", R.drawable.dompet),
        'E' to Pair("Ember", R.drawable.ember),
        'F' to Pair("Foto", R.drawable.foto),
        'G' to Pair("Gunting", R.drawable.gunting),
        'H' to Pair("Handuk", R.drawable.handuk),
        'I' to Pair("Ikat Pinggang", R.drawable.ikatpinggang),
        'J' to Pair("Jaket", R.drawable.jaket),
        'K' to Pair("Kipas", R.drawable.kipas),
        'L' to Pair("Laptop", R.drawable.laptop),
        'M' to Pair("Meja", R.drawable.meja),
        'N' to Pair("Neraca", R.drawable.neraca),
        'O' to Pair("Obeng", R.drawable.obeng),
        'P' to Pair("Piring", R.drawable.piring),
        'Q' to Pair("Quran", R.drawable.quran),
        'R' to Pair("Rak", R.drawable.rak),
        'S' to Pair("Sepatu", R.drawable.sepatu),
        'T' to Pair("Tas", R.drawable.tas),
        'U' to Pair("Uang", R.drawable.uang),
        'V' to Pair("Vas", R.drawable.vas),
        'W' to Pair("Wajan", R.drawable.wajan),
        'X' to Pair("X-Box", R.drawable.xbox),
        'Y' to Pair("Yoghurt", R.drawable.yogurt),
        'Z' to Pair("Zipper", R.drawable.zipper)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.materi_barang)

        hurufBarang = findViewById(R.id.hurufBarang)
        namaBarang = findViewById(R.id.namaBenda)
        gambarBarang = findViewById(R.id.gambarBenda)

        val hurufAwal = intent.getStringExtra("Barang") ?: "A"
        index = hurufList.indexOf(hurufAwal[0])

        updateBarang()

        val backButton = findViewById<ImageButton>(R.id.backBarang)
        backButton.setOnClickListener {
            finish()
        }

        findViewById<ImageButton>(R.id.next_barang).setOnClickListener {
            if (index < hurufList.size - 1) {
                index++
                updateBarang()
            }
        }

        findViewById<ImageButton>(R.id.prev_barang).setOnClickListener {
            if (index > 0) {
                index--
                updateBarang()
            }
        }
    }
    private fun updateBarang() {
        val huruf = hurufList[index]
        val data = dataHuruf[huruf]
        hurufBarang.text = hurufList[index].toString()
        if (data != null) {
            namaBarang.text = data.first
            gambarBarang.setImageResource(data.second)
        }
    }
}