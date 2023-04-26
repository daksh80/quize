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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DifficultyBinding.inflate(layoutInflater)
        subjectModel = SubjectModel()
        adapter = context?.let { DifficultyAdapter(subjectModel.Difficulty(it), it) }!!
        adapter.listener = this

    }
    override fun Difficulty(item: SubjectModel.Difficulty,position: Int) {
        val args = this.arguments
        val Subject = args?.get("Subject")
        val SubjectPos = args?.get("SubjectPos")
       // Toast.makeText(context,"MCQQuiZQ Controller ${Subject.toString()} , ${SubjectPos.toString()}",Toast.LENGTH_LONG).show()
        val Range = args?.get("Range")
        val RangePos = args?.get("RangePos")
      //  Toast.makeText(context,"test ${Range.toString()} , ${RangePos.toString()}",Toast.LENGTH_LONG).show()
        var bundle = Bundle()
        bundle.putString("Diff",item.Difficult)
        bundle.putInt("DiffPos",position)
        bundle.putString("Range",Range.toString())
        bundle.putString("RangePos",RangePos.toString())
        bundle.putString("Subject", Subject.toString())
        bundle.putString("SubjectPos", SubjectPos.toString())
        val Diff = Setting()
        Diff.arguments = bundle
        replaceFragment(Diff)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_start_quize2, container, false)
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