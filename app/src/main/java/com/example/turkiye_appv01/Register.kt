package com.example.turkiye_appv01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.turkiye_appv01.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textViewToLoginPage.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPass.text.toString()
            val confirmpass = binding.editTextConfirm.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {
                    // Firebase Authentication ile e-posta kontrolü
                    firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val signInMethods = task.result?.signInMethods ?: emptyList<String>()
                            if (signInMethods.isNotEmpty()) {
                                // E-posta daha önce kullanılmış
                                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                            } else {
                                // E-posta daha önce kullanılmamış, kayıt işlemi yapılabilir
                                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { registrationTask ->
                                    if (registrationTask.isSuccessful) {
                                        // Kayıt başarılı
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                    } else {
                                        // Kayıt başarısız
                                        Toast.makeText(this, "This email address is already in use", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        } else {
                            // E-posta kontrolünde bir hata oluştu
                            Toast.makeText(this, "There was an error checking your email.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
