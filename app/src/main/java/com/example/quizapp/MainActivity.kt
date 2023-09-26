package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.color.utilities.Score
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
    }

    lateinit var quiz: Quiz
    lateinit var trueButton : Button
    lateinit var falseButton: Button
    lateinit var questionReader: TextView
    lateinit var scoreReader: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button2_main_false)
        questionReader = findViewById(R.id.text_main_questions)
        scoreReader = findViewById(R.id.textView2_main_score)
        scoreReader.visibility = View.INVISIBLE

        // initial question set-up
        loadQuestions()
        questionReader.text = quiz.loadNextQuestion()

        // TODO: set up listener methods
        // send to companion object ANSWER
        trueButton.setOnClickListener {
            quiz.checkAnswer("true")
            // go to next question
            questionReader.text = quiz.loadNextQuestion()
            ifEnd()
        }

        falseButton.setOnClickListener {
            quiz.checkAnswer("false")
            // go to next question
            questionReader.text = quiz.loadNextQuestion()
            ifEnd()
        }
    }

    @SuppressLint("SetTextI18n")
    fun ifEnd() {
        if(questionReader.text == "End of Quiz"){
            falseButton.visibility = View.GONE
            trueButton.visibility = View.GONE
            scoreReader.visibility = View.VISIBLE

            scoreReader.text = "Score: ${quiz.score}"
        }
    }

    private fun loadQuestions() {
        // get JSON questions from "raw" folder
        val inputStream = resources.openRawResource(R.raw.quesitons)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: jsonString $jsonString")

        // parse json quesitons
        val gson = Gson()
        val sType = object: TypeToken<List<Question>>() {}.type
        val questions = gson.fromJson<List<Question>>(jsonString, sType)
        Log.d(TAG, "Load Quesitons $questions")

        quiz = Quiz(questions)
    }
}