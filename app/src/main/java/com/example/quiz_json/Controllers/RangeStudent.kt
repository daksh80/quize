package com.example.quiz_json.Controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.*
import com.example.quiz_json.Adapter.RangeAdapter
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.databinding.RangeStudentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RangeStudent.newInstance] factory method to
 * create an instance of this fragment.
 */
class RangeStudent : Fragment() , RangeItemClickListner {
    // TODO: Rename and change types of parameters
    // Define variables
    lateinit var binding: RangeStudentBinding
    lateinit var ranges: SubjectModel
    lateinit var adapter: RangeAdapter

    // Method called when creating the fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the RangeStudentBinding layout
        binding = RangeStudentBinding.inflate(layoutInflater)

        // Create a new SubjectModel object and set the adapter for the RangeAdapter
        ranges = SubjectModel()
        adapter = context?.let { RangeAdapter(ranges.Ranges(it), it) }!!

        // Set the listener for the RangeAdapter
        adapter.Ranlistener = this
    }

    // Method called when a range item is clicked
    override fun rangeIntemClicked(items: SubjectModel.Ranges, position: Int) {

        // Get the arguments passed in to the fragment
        val args = this.arguments
        val Subject = args?.get("Subject")
        val SubjectPos = args?.get("SubjectPos")

        // Create a new bundle with the range position and subject information
        var bundle = Bundle()
        bundle.putString("Range",items.Range)
        bundle.putInt("RangePos",position)
        bundle.putString("Subject", Subject.toString())
        bundle.putString("SubjectPos", SubjectPos.toString())

        // Create a new Difficulty fragment and set the arguments
        val Range = Difficulty()
        Range.arguments = bundle

        // Replace the current fragment with the Difficulty fragment
        replaceFragment(Range)
    }

    // Method called when creating the view for the fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_quize2, container, false)

        // Get the RecyclerView and set its layout manager and adapter
        val recyview1 = view.findViewById<RecyclerView>(R.id.rvquize)
        recyview1.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyview1.adapter=adapter

        return view
    }

    // Define a companion object
    companion object {

        // Factory method to create a new instance of the RangeStudent fragment
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RangeStudent().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // Method to replace the current fragment with a new fragment
    fun replaceFragment(fragment: Fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }

}