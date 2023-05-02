package com.example.quiz_json

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.example.quiz_json.Controllers.BooleanQuizController
import com.example.quiz_json.Controllers.McqQuizController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Setting.newInstance] factory method to
 * create an instance of this fragment.
 */
class Setting : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mcqQuiz : McqQuizController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.setting, container, false)

        // Get references to UI elements
        var test_type = "null"
        val boolean_test = view.findViewById<RadioButton>(R.id.boolean_test)
        val mcq_test = view.findViewById<RadioButton>(R.id.Mcq_test)
        val start_quiz = view.findViewById<Button>(R.id.start_test)

        // Set up click listeners for radio buttons
        boolean_test.setOnClickListener {
            test_type = "t/f"
        }
        mcq_test.setOnClickListener {
            test_type = "mcq"
        }

        // Set up click listener for start button
        start_quiz.setOnClickListener{

            // Check which radio button was selected and start the appropriate quiz
            if (test_type == "t/f") {
                // Start a true/false quiz
                replaceFragment(BooleanQuizController())
            }
            if (test_type == "mcq") {
                // Start a multiple-choice quiz
                // Get the quiz parameters from the arguments bundle
                var bundle = Bundle()
                val args = this.arguments
                val Subject = args?.get("Subject")
                val SubjectPos = args?.get("SubjectPos")
                val Range = args?.get("Range")
                val RangePos = args?.get("RangePos")
                val Difficulty = args?.get("Diff")
                val DifficultyPos = args?.get("DiffPos")
                bundle.putString("Diff",Difficulty.toString())
                bundle.putString("DiffPos",DifficultyPos.toString())
                bundle.putString("Range",Range.toString())
                bundle.putString("RangePos",RangePos.toString())
                bundle.putString("Subject", Subject.toString())
                bundle.putString("SubjectPos", SubjectPos.toString())

                // Start the quiz with the selected parameters
                mcqQuiz = McqQuizController()
                mcqQuiz.arguments = bundle
                boolean_test.isChecked = false
                mcq_test.isChecked = false

                replaceFragment(mcqQuiz)
            }
            if(test_type == "null") {
                // Display an error message if no radio button was selected
                Toast.makeText(context,"Please make a selection", Toast.LENGTH_SHORT).show()
            }
        }

        // Get quiz parameters from arguments bundle (if any)
        val args = this.arguments
        val inputData1 = args?.get("Diff")
        val inputPos1 = args?.get("DiffPos")
//        Toast.makeText(context,"helloposition ${inputData1.toString()} , ${inputPos1.toString()}",Toast.LENGTH_LONG).show()

        return view
    }

    companion object {


/**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Setting.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Setting().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun replaceFragment(fragment: Fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()

    }
}