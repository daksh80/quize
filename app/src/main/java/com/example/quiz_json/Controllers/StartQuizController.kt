package com.example.quiz_json.Controllers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.R
import com.example.quiz_json.SubjectAdapter
import com.example.quiz_json.databinding.StartQuizBinding

class StartQuizController : Fragment() {
    lateinit var binding: StartQuizBinding
    lateinit var subjectModel: SubjectModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartQuizBinding.inflate(layoutInflater)
        subjectModel = SubjectModel()


    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_quize2, container, false)
        val questions = subjectModel.subject(requireContext())

        val recyview1 = view.findViewById<RecyclerView>(R.id.rvquize)
        recyview1.layoutManager = LinearLayoutManager(requireContext())
        val itemAdapter = SubjectAdapter(questions, requireContext())
        recyview1.adapter = itemAdapter
        return  view
    }
}


