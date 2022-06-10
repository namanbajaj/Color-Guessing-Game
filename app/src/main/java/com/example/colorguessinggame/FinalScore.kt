package com.example.colorguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinalScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_score)

        val finScoreText = findViewById<TextView>(R.id.finalScoreText)
        finScoreText.setText(intent.getStringExtra("Score"))

        val restart = findViewById<Button>(R.id.restartButton)
        restart.setOnClickListener{
            startActivity(Intent(this, GameScreen::class.java))
        }
    }
}