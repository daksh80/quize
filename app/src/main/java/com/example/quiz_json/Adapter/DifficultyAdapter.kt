package com.example.quiz_json.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.DifficultyItemClickListner
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.R

class DifficultyAdapter(val items: ArrayList<SubjectModel.Difficulty>, val context: Context)  :
    RecyclerView.Adapter<DifficultyAdapter.ViewHolder>() {

    lateinit var listener: DifficultyItemClickListner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.difficulty, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val Diff = view.findViewById<TextView>(R.id.difficulty)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = items.get(position)
        holder.Diff.text = items.Difficult
        holder.itemView.setOnClickListener {
            listener?.Difficulty(items,position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}