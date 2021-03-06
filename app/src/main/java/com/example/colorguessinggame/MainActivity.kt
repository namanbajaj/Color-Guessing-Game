package com.example.colorguessinggame

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.transition.Explode
import android.transition.Slide
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleLetters = arrayListOf<TextView>(
            findViewById<TextView>(R.id.titleText1),
            findViewById<TextView>(R.id.titleText2),
            findViewById<TextView>(R.id.titleText3),
            findViewById<TextView>(R.id.titleText4),
            findViewById<TextView>(R.id.titleText5),
            findViewById<TextView>(R.id.titleText6),
            findViewById<TextView>(R.id.titleText7),
            findViewById<TextView>(R.id.titleText8),
            findViewById<TextView>(R.id.titleText9),
            findViewById<TextView>(R.id.titleText10),
            findViewById<TextView>(R.id.titleText11),
            findViewById<TextView>(R.id.titleText12),
            findViewById<TextView>(R.id.titleText13)
        )

        for(letter in titleLetters) {
            letter.typeface = Typeface.createFromAsset(assets, "crayons.ttf")
            letter.setTextColor(getNewColor())
        }

        val easyGameButton = findViewById<Button>(R.id.easymodebutton)
        val hardGameButton = findViewById<Button>(R.id.hardmodebutton)
        val impossibleGameButton = findViewById<Button>(R.id.impossiblemodebutton)

        easyGameButton.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra("Mode", "Easy")
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
        hardGameButton.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra("Mode", "Hard")
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
        impossibleGameButton.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            intent.putExtra("Mode", "Impossible")
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        lifecycleScope.launch {
            while(true){
                delay(500)
                for(letter in titleLetters)
                    letter.setTextColor(getNewColor())
            }
        }
    }

    fun getNewColor() : Int {
        var red = (0..255).random()
        var green = (0..255).random()
        var blue = (0..255).random()
        var color = Color.rgb(red, green, blue)
        return color
    }
}