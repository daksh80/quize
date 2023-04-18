package com.example.quiz_json.Controllers

import android.content.Context
import android.os.Bundle
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
import com.example.quiz_json.Difficulty
import com.example.quiz_json.Model.AllQuestionModel
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.R
import com.example.quiz_json.View.McqQuizView
import com.example.quiz_json.databinding.McqQuizBinding
import org.json.JSONObject
import kotlin.properties.Delegates

open class McqQuizController :Fragment() {

    lateinit var question_model : AllQuestionModel
    lateinit var  booleanQuizView: McqQuizView
    lateinit var binding: McqQuizBinding
    lateinit var Subject1 : String
    lateinit var SubjectPos1: String
    lateinit var Range1: String
    lateinit var RangePos1 : String
    lateinit var Difficulty1: String
    lateinit var DifficultyPos1 : String
    private lateinit var UserScoreViewModel1: UserScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = McqQuizBinding.inflate(layoutInflater)

        //setContentView(R.layout.mcq_quiz)
        question_model = AllQuestionModel()   // instance of the class
        booleanQuizView = McqQuizView()
        val args = this.arguments
        val Subject = args?.get("Subject")
        Subject1 = Subject.toString()
        val SubjectPos = args?.get("SubjectPos")
        SubjectPos1=SubjectPos.toString()
       // Toast.makeText(context,"MCQ ${Subject1} , ${SubjectPos1}",Toast.LENGTH_LONG).show()
        val Range = args?.get("Range")
        Range1 = Range.toString()
        val RangePos = args?.get("RangePos")
        RangePos1 = RangePos.toString()
        //Toast.makeText(context,"test ${Range.toString()} , ${RangePos.toString()}",Toast.LENGTH_LONG).show()
        val Difficulty = args?.get("Diff")
        Difficulty1 = Difficulty.toString()
        val DifficultyPos = args?.get("DiffPos")
        DifficultyPos1=DifficultyPos.toString()
       // Toast.makeText(context,"test ${Difficulty.toString()} , ${DifficultyPos.toString()}",Toast.LENGTH_LONG).show()


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
        UserScoreViewModel1 = ViewModelProvider(this).get(UserScoreViewModel::class.java)
        val user = UserScore(0,"a","b","c","d","e","f")
        UserScoreViewModel1.addUser(user)

        val questions = getmcqquestions()  // calling the function using the instance
        context?.let {
            booleanQuizView.setdata(questions,
                it,question_text,option1,option2,option3,option4,previous_question,next_question)
        }
        return  view
    }
    fun getmcqquestions(): Array<AllQuestionModel.McqQuestion> {


        val jsonString = requireContext().assets.open("subject.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val englishArray =
            jsonObject.getJSONArray("Subject1").getJSONObject(SubjectPos1.toInt()).getJSONArray(Subject1)
        val levelArray =
            englishArray.getJSONObject(RangePos1.toInt()).getJSONArray(Range1).getJSONObject(DifficultyPos1.toInt()).getJSONArray(Difficulty1)

        val booleanQuestions = Array(levelArray.length()) { i ->
            val jsonObj = levelArray.getJSONObject(i)
            AllQuestionModel.McqQuestion(
                jsonObj.getString("question"),
                jsonObj.getString("Option1"),
                jsonObj.getString("Option2"),
                jsonObj.getString("Option3"),
                jsonObj.getString("Option4"),
                jsonObj.getString("correctIndex")
            )
        }
        return booleanQuestions
    }

}