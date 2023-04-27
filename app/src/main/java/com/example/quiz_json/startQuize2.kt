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
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var scoretxt: TextView
    private lateinit var totalques: TextView
    private lateinit var percentage: TextView
    private var score: String? = null
    private lateinit var totalquestion: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_quize2, container, false)
        val recyview = view.findViewById<RecyclerView>(R.id.rvquize)
        recyview.layoutManager = LinearLayoutManager(context)
        val itemAdapter = context?.let { QuizeAdapter(getmcqquestions(it), it) }
        recyview.adapter = itemAdapter
        return view
    }

    private fun getmcqquestions(context: Context): Array<QuestionModel.QuizItem> {
        val sharedPreferences = context.getSharedPreferences("quiz_items", Context.MODE_PRIVATE)
        val noOfAttempts = sharedPreferences.getInt("noOfAttempts", 0)
        return Array(noOfAttempts) { i ->
            val scorejson = sharedPreferences.getString("score_$i", null)
            val totalquestionjson = sharedPreferences.getString("TotalQuestion_$noOfAttempts", null)
            if (scorejson !=null) {
                 score = Gson().fromJson(scorejson,scorejson::class.java)
            }
            if(totalquestionjson!=null) {
                totalquestion = Gson().fromJson(totalquestionjson,totalquestionjson::class.java)
            }
            QuestionModel.QuizItem(score.toString(), totalquestion.toString(), 10)
        }
    }

    companion object {
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
