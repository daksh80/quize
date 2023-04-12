package com.example.quiz_json

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Model.SubjectModel

class RangeAdapter(val items: ArrayList<SubjectModel.Ranges>, val context: Context)  :
    RecyclerView.Adapter<RangeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RangeAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.range_student, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RangeAdapter.ViewHolder, position: Int) {
        val items = items.get(position)
        holder.ageRange.text = items.Range

    }

    override fun getItemCount(): Int {
       return items.size
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ageRange = view.findViewById<TextView>(R.id.range)
    }
}
