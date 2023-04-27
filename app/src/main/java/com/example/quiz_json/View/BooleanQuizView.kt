package com.example.quiz_json.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quiz_json.Model.QuestionModel
import com.google.gson.Gson

class BooleanQuizView {

    val myAnswers = BooleanArray(10)
    var flag = true
    var count = 0
    var score = 0

    @SuppressLint("SuspiciousIndentation")
    fun setdata(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        context: Context,
        trueButton: Button,
        falseButton: Button,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button
    ) {
        questionText.text = booleanQuestions[count].question

        trueButton.setOnClickListener {
            myAnswers[count] = true
        }

        falseButton.setOnClickListener {
            myAnswers[count] = false
        }

        nextButton.setOnClickListener {
            nextQuestion(context, booleanQuestions, questionText, previousButton, nextButton)
        }

        previousButton.setOnClickListener {
            previousQuestion(booleanQuestions, questionText, previousButton, nextButton)
        }
    }

    private fun previousQuestion(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button
    ) {
        if (count > 0) {
            count--
            questionText.text = booleanQuestions[count].question
        }
    }

    fun nextQuestion(
        context: Context,
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button
    ) {
        if (count < 9) {
            count++
            questionText.text = booleanQuestions[count].question
        } else {
            score = 0
            for (i in 0 until count) {
                if (booleanQuestions[i].answer == myAnswers[i]) score++
            }
            Toast.makeText(context, "Total score: $score", Toast.LENGTH_LONG).show()
            saveData(score, context, count)
        }
    }

    private fun saveData(score: Int, context: Context, count: Int) {
        val prefs = context.getSharedPreferences("quiz_items", Context.MODE_PRIVATE)
        var noOfAttempts = prefs.getInt("noOfAttempts",0)
        noOfAttempts++
        // Get an editor to modify the SharedPreferences
        val editor = prefs?.edit()
        // Store a string value
        editor?.putInt("noOfAttempts",noOfAttempts)
        editor?.putString("score_$noOfAttempts",Gson().toJson(score))
        editor?.putString("TotalQuestion_$noOfAttempts", Gson().toJson(count))
        // Commit the changes
        editor?.apply()
    }

}
