package com.example.quiz_json

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Controllers.StartQuizController
import com.example.quiz_json.Model.SubjectModel

class DifficultyAdapter(val items: ArrayList<SubjectModel.Difficulty>, val context: Context)  :
    RecyclerView.Adapter<DifficultyAdapter.ViewHolder>() {

    lateinit var listener: DifficultyItemClickListner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DifficultyAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.difficulty, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val Diff = view.findViewById<TextView>(R.id.difficulty)
    }

    override fun onBindViewHolder(holder: DifficultyAdapter.ViewHolder, position: Int) {
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