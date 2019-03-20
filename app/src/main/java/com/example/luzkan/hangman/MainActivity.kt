package com.example.luzkan.hangman

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play.setOnClickListener{
            val intent = Intent(this,PlayActivity::class.java)
            val guessWordsArray: Array<String> = resources.getStringArray(R.array.guessWords)

            val rand = Random().nextInt(guessWordsArray.size-0)+0
            val secretWord = guessWordsArray[rand]

            intent.putExtra("secretWord", secretWord)
            startActivity(intent)
        }

    }
}
