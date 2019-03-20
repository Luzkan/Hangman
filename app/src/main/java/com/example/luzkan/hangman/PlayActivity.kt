package com.example.luzkan.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play.*
import java.util.*

class PlayActivity : AppCompatActivity() {

    // When kill reaches 10 - player loses.
    private var kill = 0
    private var secretWord = ""
    private var secretDisplay = ""
    private val correctGuesses = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val extras = intent.extras
        if (extras != null) {
            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            secretWord = extras.getString("secretWord")
        }

        prepGame()
    }

    fun guessTry(click: View) {
        if (click === tryButton) {
            val pGuess = playerGuess.text.toString().toLowerCase()

            // Player asks for a letter
            if (pGuess.length == 1) {
                if (pGuess in secretWord.toLowerCase()) {

                    correctGuesses.add(pGuess)
                    refactorSecret()

                    Toast.makeText(applicationContext,"Good guess!",Toast.LENGTH_SHORT).show()
                    checkWin()
                    return
                }
            }

            // Player tries to guess
            if (pGuess.length > 1) {
                if (pGuess.toLowerCase() == secretWord.toLowerCase()) {
                    winDialogPopUp(true)
                    return
                }
            }

            // Player fails
            kill++
            when (kill) {
                1 -> hangmanDrawing.setImageResource(R.drawable.hangman1)
                2 -> hangmanDrawing.setImageResource(R.drawable.hangman2)
                3 -> hangmanDrawing.setImageResource(R.drawable.hangman3)
                4 -> hangmanDrawing.setImageResource(R.drawable.hangman4)
                5 -> hangmanDrawing.setImageResource(R.drawable.hangman5)
                6 -> hangmanDrawing.setImageResource(R.drawable.hangman6)
                7 -> hangmanDrawing.setImageResource(R.drawable.hangman7)
                8 -> hangmanDrawing.setImageResource(R.drawable.hangman8)
                9 -> hangmanDrawing.setImageResource(R.drawable.hangman9)
                10 -> {
                    hangmanDrawing.setImageResource(R.drawable.hangman10)
                    winDialogPopUp(false)
                }
            }
        }
    }

    // Recreate display of the secret word based on progress
    private fun refactorSecret() {
        secretDisplay = ""
        secretWord.forEach {
                s -> secretDisplay += (checkIfGuessed(s.toString()))
        }
        toBeGuessed.text = secretDisplay
    }

    // Reveal correctly guessed letters
    private fun checkIfGuessed(s: String) : String {
        return when (correctGuesses.contains(s.toLowerCase())) {
            true -> s
            false -> "_"
        }
    }

    // If a char from secretWord isn't in guess chars then player didn't guess everything yet
    private fun checkWin() {
        var everythingGuessed = true
        secretWord.toLowerCase().forEach { c ->
            if (!correctGuesses.contains(c.toString()))
                everythingGuessed = false
        }
        if(everythingGuessed)
            winDialogPopUp(true)
    }

    // Win/Lose alert
    private fun winDialogPopUp(won: Boolean) {
        val builder = AlertDialog.Builder(this@PlayActivity)
        if(won) {
            builder.setTitle("Congratulations!")
        }else{
            builder.setTitle("Boo! You hanged a man!")
        }
        builder.setMessage("Do you want to play again?")

        builder.setPositiveButton("Let's go"){ _, _ ->

            // This one below is in main view more readable
            secretWord = resources.getStringArray(R.array.guessWords)[Random().nextInt(resources.getStringArray(R.array.guessWords).size-0)+0]
            prepGame()

            Toast.makeText(applicationContext,"New game started!",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){ _, _ ->
            finish()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Reset & Create New Game
    private fun prepGame(){
        hangmanDrawing.setImageResource(R.drawable.hangman0)
        kill = 0
        secretDisplay = ""
        correctGuesses.clear()

        repeat(secretWord.length) {
            secretDisplay += "_"
        }

        toBeGuessed.text = secretDisplay
    }
}

