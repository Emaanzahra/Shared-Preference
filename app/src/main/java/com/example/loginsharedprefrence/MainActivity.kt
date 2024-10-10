package com.example.loginsharedprefrence

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsharedprefrence.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("LoginPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val savedName = sharedPref.getString("username", null)
        val savedPassword = sharedPref.getString("password", null)

        if (savedName != null && savedPassword != null){
            Toast.makeText(this, "Logged in $savedName", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.button.setOnClickListener {
            val username = binding.editTextText.text.toString()
            val password = binding.editTextText2.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()){
                editor.putString("username", username)
                editor.putString("password", password)

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()

            }
        }
    }
}