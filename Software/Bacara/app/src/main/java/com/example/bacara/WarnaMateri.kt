package com.example.bacara

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class WarnaMateri : AppCompatActivity() {
    private val warnaList =
        listOf("Merah", "Oranye", "Hijau", "Cyan", "Coklat", "Kuning", "Magenta", "Ungu")
    private var index = 0

    private val warnaHexMap = mapOf(
        "Merah" to "#FF0000",
        "Oranye" to "#FFAE00",
        "Hijau" to "#08E900",
        "Cyan" to "#00FEE0",
        "Coklat" to "#713700",
        "Kuning" to "#FFE100",
        "Magenta" to "#FF00D9",
        "Ungu" to "#BB00FF"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.materi_warna)
        val btnBack = findViewById<ImageButton>(R.id.backWarna)
        val kiriBtn = findViewById<ImageButton>(R.id.prev_warna)
        val kananBtn = findViewById<ImageButton>(R.id.next_warna)

        val warna = intent.getStringExtra("WARNA")
        index = warnaList.indexOf(warna)
        if (index == -1) index = 0

        updateWarna()

        btnBack.setOnClickListener {
            finish() //
        }

        kiriBtn.setOnClickListener {
            if (index > 0) {
                index--
                updateWarna()
            }
        }

        kananBtn.setOnClickListener {
            if (index < warnaList.size - 1) {
                index++
                updateWarna()
            }
        }
    }

    private fun updateWarna() {
        val warna = warnaList[index]
        val warnaHex = warnaHexMap[warna] ?: ""
        val warnaView = findViewById<View>(R.id.warna)
        val hurufWarna = findViewById<TextView>(R.id.huruf_warna)
        warnaView.setBackgroundColor(Color.parseColor(warnaHex))
        hurufWarna.text = warna
    }
}
