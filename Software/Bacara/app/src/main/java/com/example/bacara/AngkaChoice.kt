package com.example.bacara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.view.Gravity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat

class AngkaChoice : AppCompatActivity() {

    private lateinit var gridAngka: GridLayout
    private var pageIndex = 0
    private val angkaList = listOf(
        listOf("1", "2", "3", "4", "5", "6", "7","8"),
        listOf("9", "10", "11", "12", "13", "14", "15", "16"),
        listOf("17", "18", "19", "20", "30", "40", "50", "60")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.choose_angka)

        gridAngka = findViewById(R.id.gridAngka)
        val btnPrev = findViewById<ImageButton>(R.id.balik)
        val btnNext = findViewById<ImageButton>(R.id.lanjut)
        val buttonBack = findViewById<ImageButton>(R.id.backAngka)

        buttonBack.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            startActivity(intent)
        }

        updateGrid()

        btnPrev.setOnClickListener {
            if (pageIndex > 0) {
                pageIndex--
                updateGrid()
            }
        }

        btnNext.setOnClickListener {
            if (pageIndex < angkaList.size - 1) {
                pageIndex++
                updateGrid()
            }
        }
    }

    private fun updateGrid() {
        gridAngka.removeAllViews()
        val currentChunk = angkaList[pageIndex]

        for (angka in currentChunk) {
            val textView = TextView(this).apply {
                typeface = ResourcesCompat.getFont(this@AngkaChoice, R.font.baloo_regular)
                text = angka
                textSize = 36f
                setPadding(50, 50, 50, 50)
                setBackgroundResource(R.drawable.bg_card)
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(this@AngkaChoice, R.color.word_yellow))
                setOnClickListener {
                    val angkaInt = angka.toInt()
                    val intent = Intent(this@AngkaChoice, AngkaMateri::class.java)
                    intent.putExtra("ANGKA", angkaInt)
                    startActivity(intent)
                }
            }
            val params = GridLayout.LayoutParams()
            params.width = 250
            params.height = 225
            params.setMargins(25, 8, 25, 8)
            gridAngka.addView(textView, params)
        }
    }
}