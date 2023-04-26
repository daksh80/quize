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
        previous_question: Button,
        next_question: Button
    )
    {
        question_text.text= booleanQuestions[count].question

        truebutton.setOnClickListener {
            myAnsers[count]=true
        }

        falsebutton.setOnClickListener {
            myAnsers[count]=false
        }
        next_question.setOnClickListener {
            nextquestion(context,booleanQuestions, question_text,previous_question,next_question)
        }
        previous_question.setOnClickListener {
            previousquestion(booleanQuestions, question_text,previous_question,next_question)
        }
    }

    private fun previousquestion(
        booleanQuestions: Array<QuestionModel.BooleanQuestion>,
        question: TextView,
        previous_question: Button,
        next_question: Button
    ) {
        if(count>0)
        {
            count--
            question.text = booleanQuestions[count].question

        }

        7
    }

    fun nextquestion(context: Context,
                     booleanQuestions: Array<QuestionModel.BooleanQuestion>,
                     question: TextView,
                     previous_question: Button,
                     next_question: Button
    ) {
        if(count<9) {
            count++
            question.text = booleanQuestions[count].question
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
