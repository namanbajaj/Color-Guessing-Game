package com.example.colorguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import org.w3c.dom.Text

class FinalScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_score)

        val finScoreText = findViewById<TextView>(R.id.finalScoreText)
        finScoreText.setText(intent.getStringExtra("Score"))

        val newHS = findViewById<TextView>(R.id.newHighScore)
        if(intent.getStringExtra("Beat") == "true")
            newHS.setText("Congrats! You got a new high score!")
        else
            newHS.isVisible = false


        val restart = findViewById<Button>(R.id.restartButton)
        restart.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}