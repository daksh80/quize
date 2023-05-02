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

    lateinit var binding: BooleanQuizBinding

    // onCreate is called when the fragment is first created
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the BooleanQuizBinding layout
        binding = BooleanQuizBinding.inflate(layoutInflater)

        // If there is a saved instance state, retrieve the score and count
        if (savedInstanceState != null) {
            BooleanQuizView().count = savedInstanceState.getInt("Count")
            BooleanQuizView().score = savedInstanceState.getInt("Score")
        }
    }

    // onCreateView is called when the fragment's UI is created
    // This function should return the root view for the fragment's layout
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.boolean_quiz, container, false)

        // Retrieve the views from the BooleanQuizBinding layout
        val question_text = view.findViewById<TextView>(R.id.question)
        val truebutton = view.findViewById<RadioButton>(R.id.true_button)
        val falsebutton = view.findViewById<RadioButton>(R.id.false_button)
        val previous_question = view.findViewById<Button>(R.id.previous)
        val next_question = view.findViewById<Button>(R.id.next)
        val fragmentbtn = view.findViewById<Button>(R.id.button)

        // Set a click listener on the fragment button to replace the current fragment with the StartQuizController
        fragmentbtn.setOnClickListener {
            replaceFragment(StartQuizController())
        }

        // Retrieve the context and questions from the QuestionModel
        val context = requireContext()
        val question_model = QuestionModel()
        val questions = question_model.getbooleanquestions(requireContext())

        // Set the data on the BooleanQuizView instance
        BooleanQuizView().setdata(
            questions,
            requireContext(),
            truebutton,
            falsebutton,
            question_text,
            previous_question,
            next_question
        )

        // Return the root view for the fragment's layout
        return view
    }

    // onAttach is called when the fragment is attached to an activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // onSaveInstanceState is called before the fragment is destroyed to save its state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the score and count in the bundle
        outState.putInt("Score", BooleanQuizView().score)
        outState.putInt("Count", BooleanQuizView().count)
    }

    // Replace the current fragment with another fragment
    private fun replaceFragment(fragment: Fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }
}
