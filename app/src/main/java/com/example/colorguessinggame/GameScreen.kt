package com.example.colorguessinggame

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.io.File
import kotlin.collections.ArrayList
import kotlin.math.abs


class GameScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        // load high score
        val highScoreField = findViewById<TextView>(R.id.highScoreText)

        val highScoreFile = File(this.filesDir, "UserScore.txt")

        var highScore = -1

        // if first time playing game
        if(!highScoreFile.exists()) {
            highScoreFile.createNewFile()
            highScoreField.setText("High Score: 0")
        }
        else{
            // read in from file
            this.openFileInput("UserScore.txt").use { stream ->
                val text = stream.bufferedReader().use {
                    it.readText()
                }
                Log.i("from high score file", "LOADED: $text")

                highScore = text.toInt()
                highScoreField.setText("High Score: " + highScore.toString())
            }
        }

//        Log.i("file dir.", highScore.toString())

        // square that changes color
        val square = findViewById<Button>(R.id.colorSquare)

        // get option buttons
        val option1 = findViewById<Button>(R.id.option1)
        val option2 = findViewById<Button>(R.id.option2)
        val option3 = findViewById<Button>(R.id.option3)
        val option4 = findViewById<Button>(R.id.option4)


        // Read in txt file of colors and their values
        val file = "colors.txt"
        val bufferReader = application.assets.open(file).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
//        Log.i("DATA FILE", data)

        // colorNames stores names of colors, colorValues stores names values of colors
        val colorNames = ArrayList<String>()
        val colorValues = ArrayList<Int>()
        val line = data.split("\n")
        for(l in line){
            val s = l.split("\t")
            if(s.size >= 2) {
//                Log.i("split 1", s[0])
//                Log.i("split 2", s[1])

                colorNames.add(s[0])
                val rgbSplit = s[1].split(" ")
                val redS = rgbSplit[0].trim()
                val greenS = rgbSplit[1].trim()
                val blueS = rgbSplit[2].trim()
//                Log.i("r", redS)
//                Log.i("g", greenS)
//                Log.i("b", blueS)

                val red : Int = if(redS  == "0")
                    0
                else
                    redS.toInt()

                val green : Int = if(greenS == "0")
                    0
                else
                    greenS.toInt()

                val blue : Int = if(blueS == "0")
                    0
                else
                    blueS.toInt()

                val cToAdd = Color.rgb(red, green, blue)
//                Log.i("Converted to color", cToAdd.toString())
                colorValues.add(cToAdd)
            }
        }
//        Log.i("Check for size", colorValues.size.toString() + " " + colorNames.size.toString())

        // do it once to begin game
        var colors = oneGameLoop(colorValues, colorNames, square)
        var rightChoice = setOptions(option1, option2, option3, option4, colorNames, colors)

        var score = 0

        // changes as user gets right answers
        val scoreField = findViewById<TextView>(R.id.scoreText)

        // for debugging, set invisible for now
        val colorDebugText = findViewById<TextView>(R.id.colorDebug)
        colorDebugText.setText(rightChoice.toString())
        colorDebugText.isVisible = false

        option1.setOnClickListener{
            if(rightChoice == 1)
                score = score + 1
            else
                gameOver(score, highScore)

            scoreField.setText("Score: " + score.toString())
            colors = oneGameLoop(colorValues, colorNames, square)
            rightChoice = setOptions(option1, option2, option3, option4, colorNames, colors)

//            Log.i("list of options", colors.toString())
            colorDebugText.setText(colorNames[colors[0]])
        }
        option2.setOnClickListener{
            if(rightChoice == 2)
                score = score + 1
            else
                gameOver(score, highScore)

            scoreField.setText("Score: " + score.toString())
            colors = oneGameLoop(colorValues, colorNames, square)
            rightChoice = setOptions(option1, option2, option3, option4, colorNames, colors)

//            Log.i("list of options", colors.toString())
            colorDebugText.setText(colorNames[colors[0]])
        }
        option3.setOnClickListener{
            if(rightChoice == 3)
                score = score + 1
            else
                gameOver(score, highScore)

            scoreField.setText("Score: " + score.toString())
            colors = oneGameLoop(colorValues, colorNames, square)
            rightChoice = setOptions(option1, option2, option3, option4, colorNames, colors)

//            Log.i("list of options", colors.toString())
            colorDebugText.setText(colorNames[colors[0]])
        }
        option4.setOnClickListener{
            if(rightChoice == 4)
                score = score + 1
            else
                gameOver(score, highScore)

            scoreField.setText("Score: " + score.toString())
            colors = oneGameLoop(colorValues, colorNames, square)
            rightChoice = setOptions(option1, option2, option3, option4, colorNames, colors)

//            Log.i("list of options", colors.toString())
            colorDebugText.setText(colorNames[colors[0]])
        }
    }

    fun getNewColor() : Int {
        var red = (0..255).random()
        var green = (0..255).random()
        var blue = (0..255).random()
        var color = Color.rgb(red, green, blue)
//        Log.i("COLORS", color.toString())
        return color
    }

    fun gameOver(curScore : Int, highScore : Int) {
        if(curScore > highScore){
            this.openFileOutput("UserScore.txt", Context.MODE_PRIVATE).use {
                it.write(curScore.toString().toByteArray())
            }
        }

        val intent = Intent(this, FinalScore::class.java)
        intent.putExtra("Score", curScore.toString())
        startActivity(intent)
    }

    fun oneGameLoop(colorValues: ArrayList<Int>, colorNames: ArrayList<String>, square: Button): ArrayList<Int> {

        fun List<Int>.closestValue(value: Int) = minByOrNull { abs(value - it) }

        var newCol = getNewColor()
        var closestVal = colorValues.closestValue(newCol)
        if (closestVal != null) {
            square.setBackgroundColor(closestVal)
        }
//        Log.i("Check for nearest", "old val: $newCol closest val: $closestVal")

        // find index of nearest value
        var indexOfClosest = 0
        for(i in 0 until colorNames.size)
            if(colorValues[i] == closestVal)
                indexOfClosest = i

//        Log.i("Color of closest", colorNames[indexOfClosest])
//        Log.i("Index of right answer", indexOfClosest.toString())

        // correct answer choice
        var rightAnswer = colorNames[indexOfClosest]
        Log.i("user entry", "right answer was $rightAnswer")

        // contains indices of all options, including right answer
        var colors = ArrayList<Int>()
        colors.add(indexOfClosest)

        // select other 'wrong' colors
        while(colors.size != 4){
            var toAdd = colorValues.closestValue(getNewColor())

            indexOfClosest = 0
            for(i in 0 until colorNames.size)
                if(colorValues[i] == toAdd)
                    indexOfClosest = i

            if(!colors.contains(indexOfClosest))
                colors.add(indexOfClosest)
        }

//        Log.i("list of options", colors.toString())
        return colors
    }

    // set option choices to new colors
    fun setOptions(
        option1: Button,
        option2: Button,
        option3: Button,
        option4: Button,
        colorNames: ArrayList<String>,
        colors: ArrayList<Int>
    ): Int {
        var rightChoice = (1..4).random()
        when(rightChoice) {
            1 -> {
                option1.setText(colorNames[colors[0]])
                option2.setText(colorNames[colors[1]])
                option3.setText(colorNames[colors[2]])
                option4.setText(colorNames[colors[3]])
            }

            2 -> {
                option1.setText(colorNames[colors[3]])
                option2.setText(colorNames[colors[0]])
                option3.setText(colorNames[colors[1]])
                option4.setText(colorNames[colors[2]])
            }

            3 -> {
                option1.setText(colorNames[colors[2]])
                option2.setText(colorNames[colors[3]])
                option3.setText(colorNames[colors[0]])
                option4.setText(colorNames[colors[1]])
            }

            4 -> {
                option1.setText(colorNames[colors[1]])
                option2.setText(colorNames[colors[2]])
                option3.setText(colorNames[colors[3]])
                option4.setText(colorNames[colors[0]])
            }
        }

        return rightChoice
    }
}