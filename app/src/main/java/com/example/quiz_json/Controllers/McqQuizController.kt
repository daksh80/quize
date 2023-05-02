package com.example.quiz_json.Controllers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quiz_json.Data.UserScore
import com.example.quiz_json.Data.UserScoreViewModel
import com.example.quiz_json.Model.AllQuestionModel
import com.example.quiz_json.R
import com.example.quiz_json.View.McqQuizView
import com.example.quiz_json.databinding.McqQuizBinding
import org.json.JSONObject

open class McqQuizController : Fragment() {

    // Declare some variables to be used later
    lateinit var question_model: AllQuestionModel
    lateinit var booleanQuizView: McqQuizView
    lateinit var binding: McqQuizBinding
    lateinit var Subject1: String
    lateinit var SubjectPos1: String
    lateinit var Range1: String
    lateinit var RangePos1: String
    lateinit var Difficulty1: String
    lateinit var DifficultyPos1: String
    private lateinit var UserScoreViewModel1: UserScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = McqQuizBinding.inflate(layoutInflater)

        // Instantiate some classes and get values from the arguments passed to this fragment
        question_model = AllQuestionModel()   // instance of the class
        booleanQuizView = McqQuizView()
        val args = this.arguments
        val Subject = args?.get("Subject")
        Subject1 = Subject.toString()
        val SubjectPos = args?.get("SubjectPos")
        SubjectPos1 = SubjectPos.toString()
        val Range = args?.get("Range")
        Range1 = Range.toString()
        val RangePos = args?.get("RangePos")
        RangePos1 = RangePos.toString()
        val Difficulty = args?.get("Diff")
        Difficulty1 = Difficulty.toString()
        val DifficultyPos = args?.get("DiffPos")
        DifficultyPos1 = DifficultyPos.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the view with the layout for this fragment
        val view = inflater.inflate(R.layout.mcq_quiz, container, false)

        // Find views and set up view model
        val question_text = view.findViewById<TextView>(R.id.question)
        val option1 = view.findViewById<RadioButton>(R.id.option1)
        val option2 = view.findViewById<RadioButton>(R.id.option2)
        val option3 = view.findViewById<RadioButton>(R.id.option3)
        val option4 = view.findViewById<RadioButton>(R.id.option4)
        val previous_question = view.findViewById<Button>(R.id.previous)
        val next_question = view.findViewById<Button>(R.id.next)
        UserScoreViewModel1 = ViewModelProvider(this).get(UserScoreViewModel::class.java)

        // Call a function to get the MCQ questions and observe the view model for changes
        getmcqquestions();
        UserScoreViewModel1.readAllData.observe(viewLifecycleOwner, Observer { it1 ->
            val questions = it1
            Log.e(tag,"${it1}")
            Toast.makeText(context,"loeoedlfsnvjf${it1}",Toast.LENGTH_LONG).show()
            context?.let {
                booleanQuizView.setdata(
                    questions,
                    it,
                    question_text,
                    option1,
                    option2,
                    option3,
                    option4,
                    previous_question,
                    next_question
                )
            }
        })
         // calling the function using the instance

        return  view
    }



    // This function retrieves MCQ questions from a JSON file and adds them to a view model
    fun getmcqquestions() {

        // Load JSON file as string
        val jsonString = requireContext().assets.open("subject.json").bufferedReader().use { it.readText() }

        // Convert string to JSONObject
        val jsonObject = JSONObject(jsonString)

        // Retrieve array of questions from the specified subject, range, and difficulty
        val englishArray =
            jsonObject.getJSONArray("Subject1").getJSONObject(SubjectPos1.toInt()).getJSONArray(Subject1)
        val levelArray =
            englishArray.getJSONObject(RangePos1.toInt()).getJSONArray(Range1).getJSONObject(DifficultyPos1.toInt()).getJSONArray(Difficulty1)

        // Create an array of UserScore objects from the retrieved questions
        val booleanQuestions = Array(levelArray.length()) { i ->
            val jsonObj = levelArray.getJSONObject(i)
            val user = UserScore(i,jsonObj.getString("question"),jsonObj.getString("Option1"),jsonObj.getString("Option2"),jsonObj.getString("Option3"),jsonObj.getString("Option4"), jsonObj.getString("correctIndex"))

            // Add UserScore object to view model
            UserScoreViewModel1.addUser(user)
        }

        // Uncomment the following line if you want to return the array of MCQ questions
        //return booleanQuestions
    }
}