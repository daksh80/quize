package com.example.quiz_json.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_json.Model.SubjectModel
import com.example.quiz_json.MyItemClickListener
import com.example.quiz_json.R

class SubjectAdapter(val items: ArrayList<SubjectModel.subject>, val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    lateinit var listener: MyItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject, parent, false)
         return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position];
        holder.textsub.text = item.Subject
        holder.itemView.setOnClickListener {
            listener?.onItemClicked(item,position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textsub = view.findViewById<TextView>(R.id.subject)
    }
}
