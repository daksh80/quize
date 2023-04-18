package com.example.quiz_json

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.quiz_json.databinding.ScoreCardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScoreCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreCard : AppCompatActivity() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : ScoreCardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScoreCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = findViewById<TextView>(R.id.score)
        val TotalQuestion = findViewById<TextView>(R.id.total_question)
        val prefs: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = prefs.getString("score", "")
        val totalquestion = prefs.getString("TotalQuestion","")

        TotalQuestion.text = totalquestion.toString()
         score.text = username.toString()
        //val totalques = totalquestion?.toInt()

       // val scorePer = (score?.times(100))?.div(totalques!!)




    }



}