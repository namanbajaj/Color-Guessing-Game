package com.example.colorguessinggame

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tText = findViewById<TextView>(R.id.titleText)

        val title = "GUESS THE COLOR"

        // set colors
        tText.text = title

        val t = Typeface.createFromAsset(assets, "crayons.ttf")
        tText.typeface = t

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