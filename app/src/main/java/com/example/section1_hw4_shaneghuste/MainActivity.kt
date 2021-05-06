package com.example.section1_hw4_shaneghuste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAPI = findViewById<Button>(R.id.mainBtnAPI)
        btnAPI.setOnClickListener {
            val intent = Intent(this, APIActivity::class.java)
            startActivity(intent)
        }
        val btnPermission = findViewById<Button>(R.id.mainBTNPermission)
        btnPermission.setOnClickListener {
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }
        val btnLibrary = findViewById<Button>(R.id.mainBTNLibrary)
        btnLibrary.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }
    }
}