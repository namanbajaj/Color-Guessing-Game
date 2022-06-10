package com.example.colorguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FinalScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_score)

        val finScoreText = findViewById<TextView>(R.id.finalScoreText)
        finScoreText.setText(intent.getStringExtra("Score"))
    }
}