package com.example.bacara

// LoginActivity.kt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        enableEdgeToEdge()


        auth = FirebaseAuth.getInstance()

        val edtEmail = findViewById<EditText>(R.id.edtEmail_login)
        val edtPassword = findViewById<EditText>(R.id.edtPassword_login)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val tvRegister = findViewById<TextView>(R.id.tv_register)
        val tvForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            userLogin(email, password)
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Fitur Lupa Password belum tersedia", Toast.LENGTH_SHORT).show()
        }

    }
        private fun userLogin(email: String, password: String){
            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "Email dan Password tidak boleh kosong !", Toast.LENGTH_SHORT).show()
                return
        }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Login gagal: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
}
