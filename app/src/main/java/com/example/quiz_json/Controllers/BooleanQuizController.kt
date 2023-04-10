package com.example.quiz_json.Controllers

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quiz_json.View.BooleanQuizView
import com.example.quiz_json.Model.QuestionModel
import com.example.quiz_json.R
import com.example.quiz_json.databinding.BooleanQuizBinding

class BooleanQuizController : Fragment() {

    lateinit var  binding: BooleanQuizBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BooleanQuizBinding.inflate(layoutInflater)



        // instance of the class
        // calling the function using the instance




            if (savedInstanceState != null) {
                BooleanQuizView().count = savedInstanceState.getInt("Count")
                BooleanQuizView().score = savedInstanceState.getInt("Score")

            }




    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.boolean_quiz,container,false)
        val question_text = view.findViewById<TextView>(R.id.question)
         val truebutton = view.findViewById<RadioButton>(R.id.true_button)
         val flasebutton = view.findViewById<RadioButton>(R.id.false_button)
        val previous_question = view.findViewById<Button>(R.id.previous)
        val next_question = view.findViewById<Button>(R.id.next)
        val fragmentbtn = view.findViewById<Button>(R.id.button)

        fragmentbtn.setOnClickListener {
            replaceFragment(StartQuizController())
        }

        val context = requireContext()
        val question_model = QuestionModel()
        val questions = question_model.getbooleanquestions(requireContext())

        BooleanQuizView().setdata(
            questions,
            requireContext(),
            truebutton,
            flasebutton,
            question_text,
            previous_question,
            next_question
        )


        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("Score", BooleanQuizView().score)
        outState.putInt("Count",BooleanQuizView().count)

    }
    private fun replaceFragment(fragment: Fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container3,fragment).commit()
    }

}

