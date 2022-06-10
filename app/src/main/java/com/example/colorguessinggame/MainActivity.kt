package com.example.colorguessinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val easyGameButton = findViewById<Button>(R.id.easymodebutton)
        val hardGameButton = findViewById<Button>(R.id.hardmodebutton)
        easyGameButton.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra("Mode", "Easy")
            startActivity(intent)
        }
        hardGameButton.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra("Mode", "Hard")
            startActivity(intent)
        }
    }
}