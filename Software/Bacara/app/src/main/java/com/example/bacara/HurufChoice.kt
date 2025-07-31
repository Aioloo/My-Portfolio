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

class HurufChoice : AppCompatActivity() {

    private lateinit var gridHuruf: GridLayout
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
        setContentView(R.layout.choose_huruf)

        gridHuruf = findViewById(R.id.gridHuruf)
        val btnPrev = findViewById<ImageButton>(R.id.btn_prev)
        val btnNext = findViewById<ImageButton>(R.id.btn_next)
        val buttonBack = findViewById<ImageButton>(R.id.backHuruf)

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
            if (pageIndex < alphabetChunks.size - 1) {
                pageIndex++
                updateGrid()
            }
        }
    }

    private fun updateGrid() {
        gridHuruf.removeAllViews()
        val currentChunk = alphabetChunks[pageIndex]

        for (letter in currentChunk) {
            val textView = TextView(this).apply {
                typeface = ResourcesCompat.getFont(this@HurufChoice, R.font.baloo_regular)
                text = letter
                textSize = 36f
                setPadding(50, 50, 50, 50)
                setBackgroundResource(R.drawable.bg_card)
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(this@HurufChoice, R.color.word_yellow))
                setOnClickListener {
                    val hurufBesar = letter[0].toString()
                    val intent = Intent(this@HurufChoice, HurufMateri::class.java)
                    intent.putExtra("HURUF", hurufBesar)
                    startActivity(intent)
                }
            }
            val params = GridLayout.LayoutParams()
            params.width = 250
            params.height = 225
            params.setMargins(25, 8, 25, 8)
            gridHuruf.addView(textView, params)
        }
    }
}
