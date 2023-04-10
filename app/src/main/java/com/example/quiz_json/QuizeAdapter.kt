package com.example.quiz_json

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quiz_json.Model.QuestionModel


class QuizeAdapter(private val items: List<QuestionModel.QuizItem>, private val context: Context) : RecyclerView.Adapter<QuizeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.scoreEditText.setText(item.score.toString())
        holder.totalQuestionsEditText.setText(item.totalQuestions.toString())
        holder.percentageEditText.setText(item.percentage.toString())

        holder.scoreEditText.setOnEditorActionListener { _, _, _ ->
            updateItemScore(item, holder.scoreEditText.text.toString().toInt())
            false
        }

        holder.totalQuestionsEditText.setOnEditorActionListener { _, _, _ ->
            updateItemTotalQuestions(item, holder.totalQuestionsEditText.text.toString().toInt())
            false
        }

        holder.percentageEditText.setOnEditorActionListener { _, _, _ ->
            updateItemPercentage(item, holder.percentageEditText.text.toString().toInt())
            false
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreEditText: TextView = itemView.findViewById(R.id.score)
        val totalQuestionsEditText: TextView = itemView.findViewById(R.id.questionAttpt)
        val percentageEditText: TextView = itemView.findViewById(R.id.percent)
    }

    private fun updateItemScore(item: QuestionModel.QuizItem, score: Int) {
        item.score = score
        saveItem(item)
    }

    private fun updateItemTotalQuestions(item: QuestionModel.QuizItem, totalQuestions: Int) {
        item.totalQuestions = totalQuestions
        saveItem(item)
    }

    private fun updateItemPercentage(item: QuestionModel.QuizItem, percentage: Int) {
        item.percentage = percentage
        saveItem(item)
    }

    private fun saveItem(item: QuestionModel.QuizItem) {
        val sharedPreferences = context.getSharedPreferences("quiz_items", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt( "_score", item.score)
        editor.putInt( "_total_questions", item.totalQuestions)
        editor.putInt("_percentage", item.percentage)
        editor.apply()
    }
}
