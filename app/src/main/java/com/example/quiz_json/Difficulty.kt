package com.example.quiz_json

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Adapter.DifficultyAdapter
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.databinding.DifficultyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Difficulty.newInstance] factory method to
 * create an instance of this fragment.
 */
class Difficulty : Fragment(), DifficultyItemClickListner {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: DifficultyBinding
    lateinit var subjectModel: SubjectModel
    lateinit var adapter: DifficultyAdapter

    // Called when the Fragment is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this Fragment
        binding = DifficultyBinding.inflate(layoutInflater)

        // Create an instance of SubjectModel
        subjectModel = SubjectModel()

        // Create a DifficultyAdapter with the Difficulty data and attach it to the RecyclerView
        adapter = context?.let { DifficultyAdapter(subjectModel.Difficulty(it), it) }!!
        adapter.listener = this
    }

    // This function is called when a difficulty item is clicked
    override fun Difficulty(item: SubjectModel.Difficulty,position: Int) {
        // Retrieve data from the arguments Bundle
        val args = this.arguments
        val Subject = args?.get("Subject")
        val SubjectPos = args?.get("SubjectPos")
        val Range = args?.get("Range")
        val RangePos = args?.get("RangePos")

        // Create a new Bundle with the selected difficulty and other data
        var bundle = Bundle()
        bundle.putString("Diff",item.Difficult)
        bundle.putInt("DiffPos",position)
        bundle.putString("Range",Range.toString())
        bundle.putString("RangePos",RangePos.toString())
        bundle.putString("Subject", Subject.toString())
        bundle.putString("SubjectPos", SubjectPos.toString())

        // Create a new instance of the Setting Fragment and pass the data to it
        val Diff = Setting()
        Diff.arguments = bundle

        // Replace the current Fragment with the new Setting Fragment
        replaceFragment(Diff)
    }

    // Called when the Fragment should create its UI
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this Fragment
        val view =  inflater.inflate(R.layout.fragment_start_quize2, container, false)

        // Get a reference to the RecyclerView and set its layout manager and adapter
        val recyview1 = view.findViewById<RecyclerView>(R.id.rvquize)
        recyview1.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyview1.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Difficulty.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Difficulty().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun replaceFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }

}