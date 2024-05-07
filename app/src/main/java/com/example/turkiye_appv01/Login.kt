package com.example.turkiye_appv01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.turkiye_appv01.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
  private  lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        binding.textViewToRegisterPage.setOnClickListener{
            val intent= Intent(this,Register::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener{
            val email=binding.editTextEmail.text.toString()
            val pass=binding.editTextPass.text.toString()

            if(email.isNotEmpty()&&pass.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,"First You Need To Register", Toast.LENGTH_SHORT).show()}
                    }



            }else{
                Toast.makeText(this,"Empty fields are not allowed", Toast.LENGTH_SHORT).show()}
        }

        }
    }
