package com.example.quiz_json.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.MyItemClickListener
import com.example.quiz_json.R

class SubjectAdapter(val items: ArrayList<SubjectModel.subject>, val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    lateinit var listener: MyItemClickListener

    // Inflate the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject, parent, false)
        return ViewHolder(itemView)
    }

    // Bind the data to the views in each item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position];
        holder.textsub.text = item.Subject
        Glide.with(context).load(item.images.toString()).into(holder.image)

        // Set a click listener for each item
        holder.itemView.setOnClickListener {
            listener?.onItemClicked(item,position)
        }
    }

    // Return the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return items.size
    }

    // Define the ViewHolder for each item in the RecyclerView
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textsub = view.findViewById<TextView>(R.id.subject)
        val image = view.findViewById<ImageView>(R.id.imagesub)
    }
}
