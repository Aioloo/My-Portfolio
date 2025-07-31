package com.example.bacara

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizWarna : AppCompatActivity() {

    data class Soal(
        val pertanyaan: String,
        val jawaban: Int,
        val pilihan: List<Int>,
        var jawabanUser: Int? = null,
        var sudahJawab: Boolean = false
    )

    private lateinit var soalText: TextView
    private lateinit var pilihanViews: List<View>
    private lateinit var buttonBack: ImageButton
    private lateinit var buttonNext: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    private val soalList = listOf(
        Soal("Yang manakah warna 'Merah' itu ??", R.color.red3, listOf(R.color.red3, R.color.green_word, R.color.word_yellow)),
        Soal("Yang manakah warna 'Hijau' itu ??", R.color.green_answer, listOf(R.color.red3, R.color.green_answer, R.color.word_yellow)),
        Soal("Yang manakah warna 'Kuning' itu ??", R.color.word_yellow, listOf(R.color.word_yellow, R.color.red3, R.color.green_word))
    )

    private var currentIndex = 0
    private var sudahMenjawab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.quiz_warna1)

        soalText = findViewById(R.id.tvPertanyaan)
        buttonBack = findViewById(R.id.back_quizwarna)
        buttonNext = findViewById(R.id.next_quizwarna)
        progressBar = findViewById(R.id.progress_quizwarna1)
        progressText = findViewById(R.id.tvProgressText)

        pilihanViews = listOf(
            findViewById(R.id.pilihanwarna1),
            findViewById(R.id.pilihanwarna2),
            findViewById(R.id.pilihanwarna3)
        )

        buttonBack.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                tampilkanSoal()
            } else {
                Toast.makeText(this, "Sudah soal pertama", Toast.LENGTH_SHORT).show()
            }
        }

        buttonNext.setOnClickListener {
            if (!sudahMenjawab) {
                Toast.makeText(this, "Silakan pilih warna terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (currentIndex < soalList.size - 1) {
                currentIndex++
                tampilkanSoal()
            } else {
                startActivity(Intent(this, QuizEnd::class.java))
                finish()
            }
        }

        pilihanViews.forEach { view ->
            view.setOnClickListener {
                if (!soalList[currentIndex].sudahJawab) {
                    val jawabanDipilih = view.tag as? Int ?: return@setOnClickListener
                    val soal = soalList[currentIndex]

                    soal.jawabanUser = jawabanDipilih
                    soal.sudahJawab = true
                    sudahMenjawab = true

                    pilihanViews.forEach { it.isClickable = false }

                    if (jawabanDipilih == soal.jawaban) {
                        view.setBackgroundColor(ContextCompat.getColor(this, R.color.green_progress))
                    } else {
                        view.setBackgroundColor(ContextCompat.getColor(this, R.color.red2))
                        val jawabanBenarView = pilihanViews.find {
                            (it.tag as? Int) == soal.jawaban
                        }
                        jawabanBenarView?.setBackgroundColor(ContextCompat.getColor(this, R.color.green_progress))
                    }
                }
            }
        }

        tampilkanSoal()
    }

    private fun tampilkanSoal() {
        val soal = soalList[currentIndex]
        soalText.text = soal.pertanyaan
        progressBar.progress = ((currentIndex + 1).toFloat() / soalList.size * 100).toInt()
        progressText.text = "Soal ${currentIndex + 1} dari ${soalList.size}"

        val pilihanAcak = soal.pilihan.shuffled()
        for (i in pilihanViews.indices) {
            val warnaId = pilihanAcak[i]
            pilihanViews[i].setBackgroundColor(ContextCompat.getColor(this, warnaId))
            pilihanViews[i].tag = warnaId
        }

        resetPilihan()

        if (soal.sudahJawab) {
            pilihanViews.forEach { it.isClickable = false }
            for (view in pilihanViews) {
                val warnaId = view.tag as? Int
                if (warnaId == soal.jawaban) {
                    view.setBackgroundColor(ContextCompat.getColor(this, R.color.green_progress))
                } else if (warnaId == soal.jawabanUser && soal.jawabanUser != soal.jawaban) {
                    view.setBackgroundColor(ContextCompat.getColor(this, R.color.red2))
                }
            }
            sudahMenjawab = true
        } else {
            pilihanViews.forEach { it.isClickable = true }
            sudahMenjawab = false
        }
    }

    private fun resetPilihan() {
        val soal = soalList[currentIndex]
        val pilihanAcak = soal.pilihan.shuffled()

        for (i in pilihanViews.indices) {
            val warnaId = pilihanViews[i].tag as? Int
            if (warnaId != null) {
                pilihanViews[i].setBackgroundColor(ContextCompat.getColor(this, warnaId))
            }
        }
    }
}
