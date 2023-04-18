package com.example.quiz_json

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Model.QuestionModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [startQuize2.newInstance] factory method to
 * create an instance of this fragment.
 */
class startQuize2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var scoretxt: TextView
    lateinit var totalques: TextView
    lateinit var percentage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val quizItems = listOf(
            QuestionModel.QuizItem( 0, 0, 0),
            QuestionModel.QuizItem( 0, 0, 0),
            QuestionModel.QuizItem( 0, 0, 0)
        )


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_quize2, container, false)
       val recyview = view.findViewById<RecyclerView>(R.id.rvquize)
        recyview.layoutManager = LinearLayoutManager(requireContext())
        val itemAdapter = context?.let { QuizeAdapter(quizItems, it) }
        recyview.adapter = itemAdapter


//        context?.let { loadData(it) }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment startQuize2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            startQuize2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    private fun loadData(context: Context){
//        val prefs: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val username = prefs.getString("score", "")
//        val totalquestion = prefs.getString("TotalQuestion","")
//        Toast.makeText(context," load data from context ${username}", Toast.LENGTH_LONG).show()
//
//        totalques.text = totalquestion
//        val score = username?.toInt()
//        val totalques = totalquestion?.toInt()
//
//        val scorePer = (score?.times(100))?.div(totalques!!)
//
//
//    }
}