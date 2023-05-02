package com.example.quiz_json.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quiz_json.DifficultyItemClickListner
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.R

// Define the adapter class and pass the list of items and the context as parameters
class DifficultyAdapter(val items: ArrayList<SubjectModel.Difficulty>, val context: Context) :
    RecyclerView.Adapter<DifficultyAdapter.ViewHolder>() {

    // Declare a listener variable for item click events
    lateinit var listener: DifficultyItemClickListner

    // Inflate the layout for each item and return a ViewHolder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.difficulty, parent, false)
        return ViewHolder(itemView)
    }

    // Define the ViewHolder class that holds references to the views in the layout
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Diff = view.findViewById<TextView>(R.id.difficulty)
        val image = view.findViewById<ImageView>(R.id.imagesub)
    }

    // Bind the data to the views in each item and set up a click listener
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = items.get(position)
        holder.Diff.text = items.Difficult
        Glide.with(context).load(items.images.toString()).into(holder.image)
        holder.itemView.setOnClickListener {
            listener?.Difficulty(items, position)
        }
    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return items.size
    }
}
