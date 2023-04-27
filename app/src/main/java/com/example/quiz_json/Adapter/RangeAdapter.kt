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
import com.example.quiz_json.R
import com.example.quiz_json.RangeItemClickListner

class RangeAdapter(val items: ArrayList<SubjectModel.Ranges>, val context: Context)  :
    RecyclerView.Adapter<RangeAdapter.ViewHolder>() {
    lateinit var Ranlistener: RangeItemClickListner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.range_student, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.ageRange.text = item.Range
        Glide.with(context).load(item.images.toString()).into(holder.image)
        holder.itemView.setOnClickListener {
            Ranlistener?.rangeIntemClicked(item,position)
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ageRange = view.findViewById<TextView>(R.id.range)
        val image = view.findViewById<ImageView>(R.id.imagesub)
    }
}
