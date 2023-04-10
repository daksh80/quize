package com.example.quiz_json.View

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quiz_json.Model.QuestionModel


class BooleanQuizView{

    val myAnsers = BooleanArray(10)
    var flag = true
    var count = 0
    var score = 0

    @SuppressLint("SuspiciousIndentation")
    fun setdata(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        context: Context,
        truebutton: Button,
        falsebutton: Button,
        question_text: TextView,
        question_number: TextView,
        next_question: Button
    )
    {
        question_text.text= booleanQuestions[count].question
        question_number.text = (count+1).toString()

        truebutton.setOnClickListener {
            Log.d("onClick", "True Button onClick Called")
//            Toast.makeText(quizActivity, "Correct Choice!", Toast.LENGTH_SHORT).show()
            //isCorrect(true,listOfQuestion[questionNumber].answer)

            myAnsers[count]=true

            nextquestion(context,booleanQuestions, question_text,question_number)

        }

        falsebutton.setOnClickListener {
            Log.d("onClick", "True Button onClick Called")
            myAnsers[count]=false
            nextquestion(context,booleanQuestions, question_text,question_number)

        }

    }

    private fun previousquestion(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        question: TextView,
        question_number: TextView
    ) {
        if(count>0)
        {
            count--
            question.text = booleanQuestions[count].question
            question_number.text = (count+1).toString()
        }

        7
    }

    fun nextquestion(context: Context,
                     booleanQuestions: Array<QuestionModel.BooleanQuestion>,
                     question: TextView,
                     question_number: TextView
    ) {
        if(count<9) {
            count++
            question.text = booleanQuestions[count].question
            question_number.text = (count + 1).toString()
        }
        else{
            score=0
            for (i in 0 until count) {
                if (booleanQuestions[i].answer == myAnsers[i]) score++
            }
            Toast.makeText(context, "total score $score", Toast.LENGTH_LONG).show()
            saveData(score,context,count)

        }

    }
    private fun saveData(score: Int, context: Context, count: Int) {
        val prefs = context.getSharedPreferences("quiz_items", Context.MODE_PRIVATE)
    // Get an editor to modify the SharedPreferences
        val editor = prefs?.edit()
    // Store a string value
        editor?.putString("score", score.toString())
        editor?.putString("TotalQuestion", count.toString())
   // Commit the changes
        editor?.apply()
    }

}
