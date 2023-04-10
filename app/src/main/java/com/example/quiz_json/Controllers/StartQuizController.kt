package com.example.quiz_json.Controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.R
import com.example.quiz_json.databinding.StartQuizBinding

class StartQuizController : Fragment() {
    lateinit var binding: StartQuizBinding
    lateinit var subjectModel: SubjectModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartQuizBinding.inflate(layoutInflater)
        subjectModel = SubjectModel()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.start_quiz, container, false)
        val questions = subjectModel.subject(requireContext())
        Toast.makeText(context, "No.of questions are ${questions.size}", Toast.LENGTH_SHORT).show()
        return  view
    }

}


