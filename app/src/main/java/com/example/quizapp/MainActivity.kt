package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
    }

    private lateinit var quiz: Quiz
    private lateinit var trueButton : Button
    private lateinit var falseButton: Button
    private lateinit var questionReader: TextView
    private lateinit var scoreReader: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button2_main_false)
        questionReader = findViewById(R.id.text_main_questions)
        scoreReader = findViewById(R.id.textView2_main_score)

        // initial question set-up
        loadQuestions()
        questionReader.text = quiz.loadNextQuestion()

        trueButton.setOnClickListener {
            quiz.checkAnswer("true")
            // go to next question
            questionReader.text = quiz.loadNextQuestion()
            // is it the end?
            ifEnd()
        }

        falseButton.setOnClickListener {
            quiz.checkAnswer("false")
            // go to next question
            questionReader.text = quiz.loadNextQuestion()
            // is it the end?
            ifEnd()
        }
    }

    private fun ifEnd() {
        if(questionReader.text == "End of Quiz"){
            falseButton.visibility = View.GONE
            trueButton.visibility = View.GONE
            questionReader.visibility = View.GONE

            val score = "${resources.getString(R.string.score)} ${quiz.score}"
            scoreReader.text = score
        }
    }

    private fun loadQuestions() {
        // get JSON questions from "raw" folder
        var inputStream = resources.openRawResource(R.raw.quesitons)
//        if(resources.getString(R.string.score) == "Puntos") {
//            inputStream = resources.openRawResource(R.raw.preguntas)
//        }
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