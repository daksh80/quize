package com.example.quiz_json.Model

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.json.JSONObject

class QuestionModel {

    // Define a data class for Boolean questions
    data class BooleanQuestion(val question: String, val answer: Boolean)

    // Read the JSON file from assets and parse it into an array of BooleanQuestion objects
    fun getbooleanquestions(context: Context): Array<BooleanQuestion> {
        val jsonString = context.assets.open("boolean_quiz_questions.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val questionsJson = json.getJSONArray("questions")
        Log.d("MainActivity", questionsJson[0].toString())
        val booleanQuestions = Array(questionsJson.length()) { i ->
            val questionJson = questionsJson.getJSONObject(i)
            BooleanQuestion(questionJson.getString("question"), questionJson.getBoolean("answer"))
        }
        Toast.makeText(context, "No.of questions are ${booleanQuestions.size}", Toast.LENGTH_SHORT).show()

        return booleanQuestions
    }

    // Define a data class for MCQ questions
    data class McqQuestion(
        val question: String,
        val option1: String,
        val option2: String,
        val option3: String,
        val option4: String,
        val answer: String
    )

    // Define a data class for quiz items
    data class QuizItem(var score: String, var totalQuestions: String, var percentage: Int)

}
