package com.example.quiz_json

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Controllers.RangeStudent
import com.example.quiz_json.Model.SubjectModel

class SubjectAdapter(val items: ArrayList<SubjectModel.subject>, val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    lateinit var listener: MyItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject, parent, false)
        return ViewHolder(itemView).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                    val fragmentManager = (context as FragmentActivity).supportFragmentManager
                    val newFragment = RangeStudent()
                    fragmentManager.beginTransaction().replace(R.id.fragment_container3, newFragment).addToBackStack("subject_to_range_student").commit()
                    items.clear()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textsub.text = item.Subject
//        holder.itemView.setOnClickListener {
//            listener?.onItemClicked(item)
//        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textsub = view.findViewById<TextView>(R.id.subject)
    }
}
