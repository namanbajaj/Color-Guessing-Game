package com.example.colorguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playGameButton = findViewById<Button>(R.id.playgamebutton)
        playGameButton.setOnClickListener{
            startActivity(Intent(this, GameScreen::class.java))
        }
    }
}