package com.example.quiz_json.Controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.quiz_json.Model.AllQuestionModel
import com.example.quiz_json.R
import com.example.quiz_json.View.McqQuizView
import com.example.quiz_json.databinding.McqQuizBinding

class McqQuizController :Fragment() {

    lateinit var question_model : AllQuestionModel
    lateinit var  booleanQuizView: McqQuizView
    lateinit var binding: McqQuizBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = McqQuizBinding.inflate(layoutInflater)

        //setContentView(R.layout.mcq_quiz)

        question_model = AllQuestionModel()   // instance of the class


        booleanQuizView = McqQuizView()


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mcq_quiz, container, false)
        val question_text = view.findViewById<TextView>(R.id.question)
        val option1 = view.findViewById<RadioButton>(R.id.option1)
        val option2 = view.findViewById<RadioButton>(R.id.option2)
        val option3 = view.findViewById<RadioButton>(R.id.option3)
        val option4 = view.findViewById<RadioButton>(R.id.option4)
        val previous_question = view.findViewById<Button>(R.id.previous)
        val next_question = view.findViewById<Button>(R.id.next)
        val questions = question_model.getmcqquestions(requireContext())  // calling the function using the instance
        context?.let {
            booleanQuizView.setdata(questions,
                it,question_text,option1,option2,option3,option4,previous_question,next_question)
        }


        return  view
    }


}