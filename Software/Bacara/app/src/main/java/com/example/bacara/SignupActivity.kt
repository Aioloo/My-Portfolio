package com.example.bacara

import android.content.Intent
import android.widget.Button
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val edtEmail = findViewById<EditText>(R.id.edt_email)
        val edtPassword = findViewById<EditText>(R.id.edt_password)
        val btnSignup = findViewById<Button>(R.id.btn_signup)
        val tvLogin = findViewById<TextView>(R.id.tv_login)

        btnSignup.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            userRegister(email, password)
        }

        tvLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun userRegister(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Email dan Password tidak boleh kosong !", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Format email tidak valid!", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, FormActivity::class.java)
                    intent.putExtra("userId", auth.currentUser?.uid)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Registrasi gagal: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

}