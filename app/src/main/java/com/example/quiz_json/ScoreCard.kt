// Import necessary libraries
package com.example.quiz_json

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Adapter.DifficultyAdapter
import com.example.quiz_json.Adapter.SubjectAdapter
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.View.McqQuizView
import com.example.quiz_json.databinding.ScoreCardBinding
import com.google.gson.Gson

// Define two parameters for fragment initialization
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**

A simple [Fragment] subclass.

Use the [ScoreCard.newInstance] factory method to

create an instance of this fragment.
 */
class ScoreCard : AppCompatActivity() {

    lateinit var binding : ScoreCardBinding
    lateinit var subjectModel: SubjectModel
    lateinit var adapter: DifficultyAdapter
    private var username: String? = null
    private lateinit var totalquestion: String

    // Override onCreate() method
    @SuppressLint("SuspiciousIndentation", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


                // Inflate the layout for this fragment
                binding = ScoreCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        // Find views by ID
        val score = findViewById<TextView>(R.id.score)
        val TotalQuestion = findViewById<TextView>(R.id.total_question)
        val starQuize = findViewById<Button>(R.id.startQuize12)

        // Get shared preferences for quiz items
        val prefs: SharedPreferences? =getSharedPreferences("quiz_items", MODE_PRIVATE)
        val noOfAttempts = prefs?.getInt("noOfAttempts",0)
        val scorejson = prefs?.getString("score_$noOfAttempts",null)
        val totalquestionjson = prefs?.getString("TotalQuestion_$noOfAttempts",null)

        // Get the score and total question from the shared preferences
        if(scorejson !=null) {
            username = scorejson.toString()
        }
        if(totalquestionjson !=null) {
            totalquestion =totalquestionjson.toString()
        }

        // Set onClickListener for the start quiz button
        starQuize.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            applicationContext.startActivity(intent)
        }

        // Set the score and total question text
        TotalQuestion.text = totalquestion
        score.text = username
    }
}





