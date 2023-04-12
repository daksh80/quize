package com.example.quiz_json.Model

import android.content.Context
import android.view.View
import org.json.JSONObject

class SubjectModel {

    data class subject(
        val Subject : String,
        //val image: String
    )


    val userList: ArrayList<SubjectModel.subject> = ArrayList()
    fun subject(context: Context?): ArrayList<subject> {
        val jsonString = context!!.assets.open("subject.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val subjectJson = json.getJSONArray("Subject1")
         for(i in 0 until subjectJson.length()) {
             val questionJson = subjectJson.getJSONObject(i)
             val subjectprimary = subject(
                 questionJson.getString("Subject"),
                 //questionJson.getString("image")
             )
             userList.add(subjectprimary)

         }



       return userList
    }

    data class Ranges(
        val Range: String
    )

    val rangelist: ArrayList<SubjectModel.Ranges> = ArrayList()

    fun Ranges(context: Context?): ArrayList<SubjectModel.Ranges>{
        val jsonString = context!!.assets.open("subject.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val SubRangejson  = json.getJSONArray("Ranges")
        for(i in 0 until SubRangejson.length()){
            val Rangejson = SubRangejson.getJSONObject(i)
            val studRange = Ranges(
                Rangejson.getString("Range")
            )
            rangelist.add(studRange)
        }
        return rangelist
    }
}


