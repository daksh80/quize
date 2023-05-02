package com.example.quiz_json

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Adapter.QuizeAdapter
import com.example.quiz_json.Model.QuestionModel
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class startQuize2 : Fragment() {
    // Parameters to pass to this fragment
    private var param1: String? = null
    private var param2: String? = null

    // Views in the fragment
    private lateinit var scoretxt: TextView
    private lateinit var totalques: TextView
    private lateinit var percentage: TextView
    private var score: String? = null
    private lateinit var totalquestion: String

    // Create the fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Create the view for the fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_quize2, container, false)
        // Set up the RecyclerView for the quiz items
        val recyview = view.findViewById<RecyclerView>(R.id.rvquize)
        recyview.layoutManager = LinearLayoutManager(context)
        val itemAdapter = context?.let { QuizeAdapter(getmcqquestions(it), it) }
        recyview.adapter = itemAdapter
        return view
    }

    // Get the quiz items from shared preferences and create the quiz item array
    private fun getmcqquestions(context: Context): Array<QuestionModel.QuizItem> {
        val sharedPreferences = context.getSharedPreferences("quiz_items", Context.MODE_PRIVATE)
        val noOfAttempts = sharedPreferences.getInt("noOfAttempts", 0)
        return Array(noOfAttempts) { i ->
            // Get the score and total question strings from shared preferences
            val scorejson = sharedPreferences.getString("score_$i", null)
            val totalquestionjson = sharedPreferences.getString("TotalQuestion_$noOfAttempts", null)
            // Convert the score and total question strings to their respective data types
            if (scorejson !=null) {
                score = Gson().fromJson(scorejson,scorejson::class.java)
            }
            if(totalquestionjson!=null) {
                totalquestion = Gson().fromJson(totalquestionjson,totalquestionjson::class.java)
            }
            // Create a new QuizItem with the score, total questions, and a default value for the time taken
            QuestionModel.QuizItem(score.toString(), totalquestion.toString(), 10)
        }
    }

    companion object {
        // Create a new instance of this fragment with the specified parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            startQuize2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
