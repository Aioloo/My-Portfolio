package com.example.bacara

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class WarnaChoice : AppCompatActivity() {

    private lateinit var gridWarna: GridLayout
    private var pageIndex = 0

    private val warnaList = listOf(
        listOf("Merah", "Oranye", "Hijau", "Cyan"),
        listOf("Coklat", "Kuning", "Magenta", "Ungu")
    )

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
        setContentView(R.layout.choose_warna)

        gridWarna = findViewById(R.id.gridWarna)
        val btnPrev = findViewById<ImageButton>(R.id.back_warna)
        val btnNext = findViewById<ImageButton>(R.id.next_warna)
        val buttonBack = findViewById<ImageButton>(R.id.backWarna)

        buttonBack.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            startActivity(intent)
        }

        btnPrev.setOnClickListener {
            if (pageIndex > 0) {
                pageIndex--
                updateGrid()
            }
        }

        btnNext.setOnClickListener {
            if (pageIndex < warnaList.size - 1) {
                pageIndex++
                updateGrid()
            }
        }

        updateGrid()
    }

    private fun updateGrid() {
        gridWarna.removeAllViews()
        val currentChunk = warnaList[pageIndex]

        for (warna in currentChunk) {
            val kotakWarna = FrameLayout(this).apply {
                background = ContextCompat.getDrawable(this@WarnaChoice, R.drawable.bg_card)

                val warnaOverlay = View(this@WarnaChoice).apply{
                    setBackgroundColor(Color.parseColor(warnaHexMap[warna]))
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT

                    ).apply {
                        setMargins(16, 16, 32, 16)
                    }
                }

                addView(warnaOverlay)

                setOnClickListener{
                    val intent = Intent(this@WarnaChoice, WarnaMateri::class.java)
                    intent.putExtra("WARNA", warna)
                    startActivity(intent)
                }
            }

            val params = GridLayout.LayoutParams().apply {
                width = 250
                height = 225
                setMargins(25, 8, 25, 8)
            }

            gridWarna.addView(kotakWarna, params)
        }
    }
}
