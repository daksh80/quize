package com.example.quiz_json.Controllers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.*
import com.example.quiz_json.Adapter.SubjectAdapter
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.databinding.StartQuizBinding

class StartQuizController : Fragment(), MyItemClickListener {
    lateinit var binding: StartQuizBinding
    lateinit var subjectModel: SubjectModel
    lateinit var adapter: SubjectAdapter

    // onCreate() function of the fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the binding object for the fragment
        binding = StartQuizBinding.inflate(layoutInflater)
        // Create an instance of the SubjectModel class
        subjectModel = SubjectModel()
        // Create an instance of the SubjectAdapter class and pass the subject list and context to it
        adapter = context?.let { SubjectAdapter(subjectModel.subject(it), it) }!!
        // Set the click listener for the subject adapter
        adapter.listener = this
    }

    // onItemClicked() function of the MyItemClickListener interface
    override fun onItemClicked(item: SubjectModel.subject, position: Int) {
        // Create a bundle object and add the subject and its position to it
        var bundle= Bundle()
        bundle.putString("Subject",item.Subject)
        bundle.putInt("SubjectPos",position)
        // Create an instance of the RangeStudent fragment and pass the bundle to it
        val Range = RangeStudent()
        Range.arguments = bundle
        // Replace the current fragment with the RangeStudent fragment
        replaceFragment(Range)
    }

    // onCreateView() function of the fragment
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_quize2, container, false)
        // Find the RecyclerView object in the layout
        val recyview1 = view.findViewById<RecyclerView>(R.id.rvquize)
        // Set the layout manager for the RecyclerView object
        recyview1.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        // Set the adapter for the RecyclerView object
        recyview1.adapter = adapter

        return view
    }

    // A helper function to replace the current fragment with a new fragment
    private fun replaceFragment(fragment: Fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }
}
