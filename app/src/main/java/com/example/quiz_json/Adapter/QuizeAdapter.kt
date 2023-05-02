package com.example.quiz_json.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Model.QuestionModel
import com.example.quiz_json.R


class QuizeAdapter(private val items: Array<QuestionModel.QuizItem>, private val context: Context) : RecyclerView.Adapter<QuizeAdapter.ViewHolder>() {

    /**
     * This function inflates the layout for each item in the RecyclerView and returns a ViewHolder object.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_user, parent, false)
        return ViewHolder(view)
    }

    /**
     * This function binds the data from the QuizItem object to the views in the ViewHolder.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.scoreEditText.setText(item.score.toString())
        holder.totalQuestionsEditText.setText(item.totalQuestions.toString())
        holder.percentageEditText.setText(item.percentage.toString())
    }

    /**
     * This function returns the number of items in the RecyclerView.
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * This is the ViewHolder class that holds references to the views in the layout.
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreEditText: TextView = itemView.findViewById(R.id.score)
        val totalQuestionsEditText: TextView = itemView.findViewById(R.id.questionAttpt)
        val percentageEditText: TextView = itemView.findViewById(R.id.percent)
    }

}
