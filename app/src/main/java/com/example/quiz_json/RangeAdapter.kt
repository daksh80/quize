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
    lateinit var Ranlistener: RangeItemClickListner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RangeAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.range_student, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.ageRange.text = item.Range
        holder.itemView.setOnClickListener {
            Ranlistener?.rangeIntemClicked(item,position)
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ageRange = view.findViewById<TextView>(R.id.range)
    }
}
