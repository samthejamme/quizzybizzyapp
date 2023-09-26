package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
        var ANSWER = "false"
    }

    lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initial question set-up
        loadQuestions()

        // TODO: set up listener methods
        // send to companion object ANSWER
    }

    private fun loadQuestions() {
        // get JSON questions from "raw" folder
        val inputStream = resources.openRawResource(R.raw.quesitons)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
//        Log.d(TAG, "onCreate: jsonString $jsonString")

        // parse json quesitons
        val gson = Gson()
        val sType = object: TypeToken<List<String>>() {}.type
        val questions = gson.fromJson<List<Question>>(jsonString, sType)
        Log.d(TAG, "Load Quesitons $questions")

        quiz = Quiz(questions)
    }
}