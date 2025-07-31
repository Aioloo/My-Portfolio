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

class BarangChoice : AppCompatActivity() {

    private lateinit var gridBarang: GridLayout
    private var pageIndex = 0
    private val alphabetChunks = listOf(
        listOf("Aa", "Bb", "Cc", "Dd", "Ee", "Ff", "Gg", "Hh"),
        listOf("Ii", "Jj", "Kk", "Ll", "Mm", "Nn", "Oo", "Pp"),
        listOf("Qq", "Rr", "Ss", "Tt", "Uu", "Vv", "Ww", "Xx"),
        listOf("Yy", "Zz")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.choose_barang)

        gridBarang = findViewById(R.id.gridBarang)
        val btnPrev = findViewById<ImageButton>(R.id.balik_barang)
        val btnNext = findViewById<ImageButton>(R.id.lanjut_barang)
        val buttonBack = findViewById<ImageButton>(R.id.backBarang)

        buttonBack.setOnClickListener {
            val intent = Intent(this, transisiActivity::class.java)
            startActivity(intent)
            finish()
        }

        updateGrid()

        btnPrev.setOnClickListener {
            if (pageIndex > 0) {
                pageIndex--
                updateGrid()
            }
        }

        btnNext.setOnClickListener {
            if (pageIndex < alphabetChunks.size - 1) {
                pageIndex++
                updateGrid()
            }
        }
    }

    private fun updateGrid() {
        gridBarang.removeAllViews()
        val currentChunk = alphabetChunks[pageIndex]

        for (letter in currentChunk) {
            val textView = TextView(this).apply {
                typeface = ResourcesCompat.getFont(this@BarangChoice, R.font.baloo_regular)
                text = letter
                textSize = 36f
                setPadding(50, 50, 50, 50)
                setBackgroundResource(R.drawable.bg_card)
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(this@BarangChoice, R.color.word_yellow))
                setOnClickListener {
                    val barang = letter[0].toString()
                    val intent = Intent(this@BarangChoice, BarangMateri::class.java)
                    intent.putExtra("Barang", barang)
                    startActivity(intent)
                }
            }
            val params = GridLayout.LayoutParams()
            params.width = 250
            params.height = 225
            params.setMargins(25, 8, 25, 8)
            gridBarang.addView(textView, params)
        }
    }
}
