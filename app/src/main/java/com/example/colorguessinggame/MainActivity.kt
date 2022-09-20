package com.example.colorguessinggame

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var job : Job

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

        job = GlobalScope.launch(Dispatchers.IO) {
            while(true) {
                delay(500)
                withContext(Dispatchers.Main) {
                    for(letter in titleLetters) {
                        letter.setTextColor(getNewColor())
                    }
                }
//                Log.i("Color", "Color changed")
            }
        }

        val easyGameButton = findViewById<Button>(R.id.easymodebutton)
        val hardGameButton = findViewById<Button>(R.id.hardmodebutton)
        val impossibleGameButton = findViewById<Button>(R.id.impossiblemodebutton)

        easyGameButton.setOnClickListener{
            startNewActivity("Easy")
        }
        hardGameButton.setOnClickListener{
            startNewActivity("Hard")
        }
        impossibleGameButton.setOnClickListener{
            startNewActivity("Impossible")
        }

        val privacypolicy = findViewById<TextView>(R.id.privacypolicybutton)
        privacypolicy.setOnClickListener{
            val intent = Intent(android.content.Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://sites.google.com/view/guess-the-color-privacy-policy/home")
            startActivity(intent)
        }
    }

    private fun getNewColor(): Int {
        var red = (0..255).random()
        var green = (0..255).random()
        var blue = (0..255).random()
        return Color.rgb(red, green, blue)
    }

    private fun startNewActivity(mode : String) {
        val intent = Intent(this, GameScreen::class.java)
        intent.putExtra("Mode", mode)
        job.cancel()
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

}