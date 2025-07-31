package com.example.bacara

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizHuruf : AppCompatActivity() {

    data class Soal(
        val pertanyaan: String,
        val jawaban: String,
        val pilihan: List<String>,
        var jawabanUser : String?= null,
        var sudahJawab: Boolean = false
    )

    private lateinit var soalText: TextView
    private lateinit var pilihan1: TextView
    private lateinit var pilihan2: TextView
    private lateinit var pilihan3: TextView
    private lateinit var buttonBack: ImageButton
    private lateinit var buttonNext: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    private val soalList = listOf(
        Soal("Yang manakah huruf A itu??", "A", listOf("A", "B", "D")),
        Soal("Yang manakah huruf F itu??", "F", listOf("F", "P", "E")),
        Soal("Yang manakah huruf M itu??", "M", listOf("Z", "M", "N"))
    )
    private var currentIndex = 0
    private var sudahMenjawab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.quiz_huruf1)

        soalText = findViewById(R.id.tvPertanyaan)
        pilihan1 = findViewById(R.id.pilihan1)
        pilihan2 = findViewById(R.id.pilihan2)
        pilihan3 = findViewById(R.id.pilihan3)
        buttonBack = findViewById(R.id.back_quizhuruf)
        buttonNext = findViewById(R.id.next_quizhuruf)
        progressBar = findViewById(R.id.progress_quiz1)
        progressText = findViewById(R.id.tvProgressText)

        progressBar.max = 100

        buttonBack.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                tampilkanSoal()
            } else {
                Toast.makeText(this, "Sudah soal pertama", Toast.LENGTH_SHORT).show()
            }
        }

        buttonNext.setOnClickListener {
            if (!sudahMenjawab){
                Toast.makeText(this, "Silahkan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (currentIndex < soalList.size - 1) {
                currentIndex++
                tampilkanSoal()
            } else {
                val intent = Intent(this, QuizEnd::class.java)
                startActivity(intent)
                finish()
            }
        }

        val pilihanViews = listOf(pilihan1, pilihan2, pilihan3)
        pilihanViews.forEach { pilihan ->
            pilihan.setOnClickListener {
                if (!soalList[currentIndex].sudahJawab) {
                    val jawabanDipilih = pilihan.text.toString()
                    val jawabanBenar = soalList[currentIndex].jawaban

                    soalList[currentIndex].jawabanUser = jawabanDipilih
                    soalList[currentIndex].sudahJawab = true

                    if (jawabanDipilih == jawabanBenar) {
                        pilihan.setBackgroundColor(ContextCompat.getColor(this, R.color.green_progress))
                    } else {
                        pilihan.setBackgroundColor(ContextCompat.getColor(this, R.color.red2))
                        pilihanViews.find { it.text == jawabanBenar }?.setBackgroundColor(
                            ContextCompat.getColor(this, R.color.green_progress)
                        )
                    }

                    sudahMenjawab = true
                    pilihanViews.forEach { it.isClickable = false }
                }
            }
        }

        tampilkanSoal()
    }

    private fun tampilkanSoal() {
        val soal = soalList[currentIndex]
        soalText.text = soal.pertanyaan

        val pilihanAcak = soal.pilihan.shuffled()
        val pilihanViews = listOf(pilihan1, pilihan2, pilihan3)
        pilihan1.text = pilihanAcak[0]
        pilihan2.text = pilihanAcak[1]
        pilihan3.text = pilihanAcak[2]

        resetPilihanWarna()
        if (soal.sudahJawab) {
            val jawabanUser = soal.jawabanUser
            pilihanViews.forEach { pilihan ->
                pilihan.isClickable = false
                when (pilihan.text) {
                    soal.jawaban -> pilihan.setBackgroundColor(ContextCompat.getColor(this, R.color.green_progress))
                    jawabanUser -> if (jawabanUser != soal.jawaban) {
                        pilihan.setBackgroundColor(ContextCompat.getColor(this, R.color.red2))
                    }
                }
            }
            sudahMenjawab = true
        } else {
            pilihanViews.forEach { it.isClickable = true }
            sudahMenjawab = false
        }

        progressBar.progress = ((currentIndex + 1).toFloat() / soalList.size * 100).toInt()
        progressText.text = "Soal ${currentIndex + 1} dari ${soalList.size}"
    }

    private fun resetPilihanWarna() {
        val pilihanViews = listOf(pilihan1, pilihan2, pilihan3)
        pilihanViews.forEach {
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.green_button))
            it.isClickable = true
        }
    }
}
