package com.example.quiz_json.View

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quiz_json.Controllers.McqQuizController
import com.example.quiz_json.Data.*
import com.example.quiz_json.Model.QuestionModel
import com.example.quiz_json.R
import com.example.quiz_json.ScoreCard
import com.google.gson.Gson

class McqQuizView :  McqQuizController() {


    val myAnsers = Array<String?>(10){null}
    private var countdownTimer: CountDownTimer? = null
    private lateinit var UserScoreViewModel1: UserScoreViewModel








    var count = 0
    var score = 0


    fun setdata(
        questions: List<UserScore>,
        context: Context,
        question_text: TextView,
        option1: RadioButton,
        option2: RadioButton,
        option3: RadioButton,
        option4: RadioButton,
        previous_question: Button,
        next_question: Button,
    ) {

        question_text.text = questions[count].question
        option1.text = questions[count].option1
        option2.text = questions[count].option2
        option3.text = questions[count].option3
        option4.text = questions[count].option4

        countdownTimer?.cancel() // Stop the countdown timer
        startCountdownTimer(questions,
        context,
        question_text,
        option1,
        option2,
        option3,
        option4,
        previous_question,
        next_question
        )



        option1.setOnClickListener {

            myAnsers[count] = option1.text as String
        }

        option2.setOnClickListener {

            myAnsers[count] = option2.text as String
        }
        option3.setOnClickListener {

            myAnsers[count] = option3.text as String
        }
        option4.setOnClickListener {

            myAnsers[count] = option4.text as String
        }

        next_question.setOnClickListener {

            nextquestion(
                questions,
                context,
                question_text,
                option1,
                option2,
                option3,
                option4,
                previous_question,
                next_question

            )
//            startCountdownTimer(questions,
//                context,
//                question_text,
//                option1,
//                option2,
//                option3,
//                option4,
//                previous_question,
//                next_question
//            )
        }

        previous_question.setOnClickListener {
            previousquestion(
                questions,
                context,
                question_text,
                option1,
                option2,
                option3,
                option4,
                previous_question,
                next_question
            )

        }
        countdownTimer?.cancel()
        startCountdownTimer(questions,
            context,
            question_text,
            option1,
            option2,
            option3,
            option4,
            previous_question,
            next_question
        )


    }

    private fun nextquestion(
        questions: List<UserScore>,
        context: Context,
        question_text: TextView,
        option1: RadioButton,
        option2: RadioButton,
        option3: RadioButton,
        option4: RadioButton,
        previous_question: Button,
        next_question: Button
    ) {
        countdownTimer?.cancel() // cancel the current countdown timer
        option1.isChecked = false
        option2.isChecked=false
        option3.isChecked = false
        option4.isChecked=false
        if(count<9) {
            count++
            question_text.text = questions[count].question
            option1.text = questions[count].option1
            option2.text = questions[count].option2
            option3.text = questions[count].option3
            option4.text = questions[count].option4
            startCountdownTimer(questions,
                context,
                question_text,
                option1,
                option2,
                option3,
                option4,
                previous_question,
                next_question
            )

        }
        else
        {
            score=0
            for (i in 0 until count) {
                if (questions[i].answer == myAnsers[i]) score++
            }
            Toast.makeText(context, "$score", Toast.LENGTH_LONG).show()
            // intent.putExtra("score",score)
            val intent = Intent(context, ScoreCard::class.java)
            context.startActivity(intent)

            Toast.makeText(context, "Check your scores here ${score}", Toast.LENGTH_LONG).show()
            saveData(score, context, count)

            UserScoreViewModel1 = ViewModelProvider(this).get(UserScoreViewModel::class.java)
            UserScoreViewModel1.deleteAllUser()
        }


    }


//    private fun nextquestion(
//        questions: List<UserScore>,
//        context: Context,
//        questionText: TextView,
//        option1: RadioButton,
//        option2: RadioButton,
//        option3: RadioButton,
//        option4: RadioButton,
//        questionNumber: TextView
//    ) {
//
//    }



    fun previousquestion(
        questions: List<UserScore>,
        context: Context,
        question_text: TextView,
        option1: RadioButton,
        option2: RadioButton,
        option3: RadioButton,
        option4: RadioButton,
        previous_question: Button,
        next_question: Button
    )
    {
        option1.isChecked = false
        option2.isChecked=false
        option3.isChecked = false
        option4.isChecked=false

        if(count>0) {
            count--
            question_text.text = questions[count].question
            option1.text = questions[count].option1
            option2.text = questions[count].option2
            option3.text = questions[count].option3
            option4.text = questions[count].option4
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
        editor?.putString("score_$noOfAttempts", Gson().toJson(score))
        editor?.putString("TotalQuestion_$noOfAttempts", Gson().toJson(count))
        // Commit the changes
        editor?.apply()
    }
    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container3, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun startCountdownTimer(
    questions: List<UserScore>,
    context: Context,
    question_text: TextView,
    option1: RadioButton,
    option2: RadioButton,
    option3: RadioButton,
    option4: RadioButton,
    previous_question: Button,
    next_question: Button
    )
    {
        // Remove the var keyword to initialize the member variable instead of creating a new local variable
        countdownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(context,"Next Question (${millisUntilFinished / 1000})",Toast.LENGTH_SHORT).show()
            }

            override fun onFinish() {
                 nextquestion(
                    questions,
                    context,
                    question_text,
                    option1,
                    option2,
                    option3,
                    option4,
                    previous_question,
                    next_question
                )
            }
        }

        countdownTimer?.start() // Use the member variable to start the timer
    }

}


