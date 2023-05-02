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

    private val myAnswers = BooleanArray(10) // an array to store the user's answers
    var count = 0 // variable to keep track of the current question number
    var score = 0 // variable to keep track of the user's score
    private var countdownTimer: CountDownTimer? =
        null // a timer to limit the time for answering each question

    // This function sets the data for the boolean quiz view
    fun setdata(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        context: Context,
        trueButton: RadioButton,
        falseButton: RadioButton,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button
    ) {

        questionText.text =
            booleanQuestions[count].question // set the text for the current question
        countdownTimer?.cancel() // Stop the countdown timer
        startCountdownTimer(
            context,
            booleanQuestions,
            questionText,
            previousButton,
            nextButton,
            trueButton,
            falseButton
        ) // start the countdown timer

        // add listeners for the true and false buttons
        trueButton.setOnClickListener {
            myAnswers[count] = true
        }

        falseButton.setOnClickListener {
            myAnswers[count] = false
        }

        // add a listener for the next button
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
//            startCountdownTimer(
//                context,
//                booleanQuestions,
//                questionText,
//                previousButton,
//                nextButton,
//                trueButton,
//                falseButton
//            )
        }

        // add a listener for the previous button
        previousButton.setOnClickListener {
            previousQuestion(booleanQuestions, questionText, previousButton, nextButton)
        }
        countdownTimer?.cancel() // cancel the countdown timer (if it exists) to avoid any conflicts

        startCountdownTimer(
            context,
            booleanQuestions,
            questionText,
            previousButton,
            nextButton,
            trueButton,
            falseButton
        ) // start the countdown timer again
    }


    // This function handles going to the previous question
    private fun previousQuestion(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button
    ) {
        if (count > 0) { // if there are previous questions
            count-- // go to the previous question
            questionText.text =
                booleanQuestions[count].question // set the text for the previous question
        }
    }

    // This function starts the countdown timer for each question
    private fun startCountdownTimer(
        context: Context,
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        questionText: TextView,
        previousButton: Button,
        nextButton: Button,
        trueButton: RadioButton,
        falseButton: RadioButton
    ) {
        countdownTimer?.cancel() // cancel the countdown timer (if it exists) to avoid any conflicts
        // Remove the var keyword to initialize the member variable instead of creating a new local variable
        countdownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(
                    context,
                    "Next Question (${millisUntilFinished / 1000})",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFinish() {
                nextQuestion(
                    context,
                    booleanQuestions,
                    questionText,
                    previousButton,
                    nextButton,
                    trueButton,
                    falseButton
                )
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
        countdownTimer?.cancel() // Stop the countdown timer
        trueButton.isChecked = false // Reset the radio buttons
        falseButton.isChecked = false

        if (count < 9) { // If there are still questions left, move to the next question
            count++
            questionText.text = booleanQuestions[count].question
            startCountdownTimer(
                context,
                booleanQuestions,
                questionText,
                previousButton,
                nextButton,
                trueButton,
                falseButton
            ) // Start a new countdown timer for the next question
        } else { // If all questions have been answered, calculate the score and save the data
            var answeredQuestions = 0
            score = 0
            for (i in 0 until count) {
                if (myAnswers[i]) { // If the question was answered
                    answeredQuestions++
                    if (booleanQuestions[i].answer) score++ // If the answer was correct, increment the score
                }
            }
            val intent = Intent(
                context,
                ScoreCard::class.java
            ) // Create an intent to move to the score card activity
            context.startActivity(intent) // Start the score card activity
            Toast.makeText(context, "${score}", Toast.LENGTH_LONG)
                .show() // Show the score as a toast message
            saveData(score, context, count) // Save the score and other data to SharedPreferences
        }
    }

    private fun saveData(score: Int, context: Context, count: Int) {
        val prefs = context.getSharedPreferences(
            "quiz_items",
            Context.MODE_PRIVATE
        ) // Get a reference to SharedPreferences
        var noOfAttempts =
            prefs.getInt("noOfAttempts", 0) // Get the number of attempts from SharedPreferences
        noOfAttempts++ // Increment the number of attempts
        val editor = prefs?.edit()
        editor?.putInt("noOfAttempts", noOfAttempts) // Save the new number of attempts
        editor?.putString(
            "score_$noOfAttempts",
            Gson().toJson(score)
        ) // Save the score for this attempt
        editor?.putString(
            "TotalQuestion_$noOfAttempts",
            Gson().toJson(count)
        ) // Save the total number of questions for this attempt
        editor?.apply() // Apply the changes to SharedPreferences
    }
}