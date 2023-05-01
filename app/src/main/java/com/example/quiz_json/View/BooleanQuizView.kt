package com.example.quiz_json.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quiz_json.Model.QuestionModel
import com.google.gson.Gson
import android.os.CountDownTimer
import android.widget.RadioButton
import com.example.quiz_json.ScoreCard

class BooleanQuizView {

    private val myAnswers = BooleanArray(10)
    var count = 0
    var score = 0
    private var countdownTimer: CountDownTimer? = null

    fun setdata(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        context: Context,
        trueButton: RadioButton,
        falseButton: RadioButton,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button
    ) {

        questionText.text = booleanQuestions[count].question
        startCountdownTimer(context, booleanQuestions, questionText, previousButton, nextButton,trueButton,falseButton)
        trueButton.setOnClickListener {
            myAnswers[count] = true
        }

        falseButton.setOnClickListener {
            myAnswers[count] = false
        }

        nextButton.setOnClickListener {
            nextQuestion(
                context,
                booleanQuestions,
                questionText,
                previousButton,
                nextButton,
                trueButton,
                falseButton
            )
            startCountdownTimer(context, booleanQuestions, questionText, previousButton, nextButton,trueButton,falseButton)
        }

        previousButton.setOnClickListener {
            previousQuestion(booleanQuestions, questionText, previousButton, nextButton)
        }
        countdownTimer?.cancel()

        startCountdownTimer(context, booleanQuestions, questionText, previousButton, nextButton,trueButton,falseButton)
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

    private fun startCountdownTimer(
        context: Context,
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button,
        trueButton: RadioButton,
        falseButton: RadioButton
    ) {
        // Remove the var keyword to initialize the member variable instead of creating a new local variable
        countdownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(context,"Next Question (${millisUntilFinished / 1000})",Toast.LENGTH_SHORT).show()
            }

            override fun onFinish() {
                nextQuestion(context, booleanQuestions, questionText, previousButton, nextButton, trueButton,falseButton)
            }
        }

        countdownTimer?.start() // Use the member variable to start the timer
    }

    private fun nextQuestion(
        context: Context,
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button,
        trueButton: RadioButton,
        falseButton: RadioButton
    ) {
        countdownTimer?.cancel()
        trueButton.isChecked= false
        falseButton.isChecked = false

        if (count < 9) {
            count++
            questionText.text = booleanQuestions[count].question
            startCountdownTimer(context, booleanQuestions, questionText, previousButton, nextButton,trueButton,falseButton)
        } else {
            var answeredQuestions = 0
            score = 0
            for (i in 0 until count) {
                if (myAnswers[i]) {
                    answeredQuestions++
                    if (booleanQuestions[i].answer) score++
                }
            }
            val intent = Intent(context, ScoreCard::class.java)
            context.startActivity(intent)
            Toast.makeText(context,"${score}",Toast.LENGTH_LONG).show()
            saveData(score, context, count)
        }
    }


    private fun saveData(score: Int, context: Context, count: Int) {
        val prefs = context.getSharedPreferences("quiz_items", Context.MODE_PRIVATE)
        var noOfAttempts = prefs.getInt("noOfAttempts", 0)
        noOfAttempts++
        val editor = prefs?.edit()
        editor?.putInt("noOfAttempts", noOfAttempts)
        editor?.putString("score_$noOfAttempts", Gson().toJson(score))
        editor?.putString("TotalQuestion_$noOfAttempts", Gson().toJson(count))
        editor?.apply()
    }
}
