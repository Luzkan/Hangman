package com.example.luzkan.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {

    // When kill reaches 10 - player loses.
    private var kill = 0
    private var secretWord = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val extras = intent.extras
        if (extras != null) {
            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            secretWord = extras.getString("secretWord")
            toBeGuessed.text = secretWord
        }
    }

    fun guessTry(click: View) {
        if (click === tryButton) {
            if(wrong()){
                kill++
                when(kill){
                    1 -> hangmanDrawing.setImageResource(R.drawable.hangman1)
                    2 -> hangmanDrawing.setImageResource(R.drawable.hangman2)
                    3 -> hangmanDrawing.setImageResource(R.drawable.hangman3)
                    4 -> hangmanDrawing.setImageResource(R.drawable.hangman4)
                    5 -> hangmanDrawing.setImageResource(R.drawable.hangman5)
                    6 -> hangmanDrawing.setImageResource(R.drawable.hangman6)
                    7 -> hangmanDrawing.setImageResource(R.drawable.hangman7)
                    8 -> hangmanDrawing.setImageResource(R.drawable.hangman8)
                    9 -> hangmanDrawing.setImageResource(R.drawable.hangman9)
                    10 -> hangmanDrawing.setImageResource(R.drawable.hangman10)
                }
            }else{
                Toast.makeText(applicationContext,"Good guess!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun winDialogPopUp(won: Boolean) {
        val builder = AlertDialog.Builder(this@PlayActivity)
        if(won) {
            builder.setTitle("Congratulations!")
        }else{
            builder.setTitle("Boo! You hanged a man!")
        }
        builder.setMessage("Do you want to play again?")

        builder.setPositiveButton("Let's go"){ _, _ ->
            Toast.makeText(applicationContext,"New game started!",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ ->
            finish()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun wrong(): Boolean {
        return true
    }

}

