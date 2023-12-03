package com.example.kotartisan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

private lateinit var startButton: ImageButton

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener { val intent = Intent(this@StartScreen, Drawing::class.java)
            startActivity(intent) }
    }
}