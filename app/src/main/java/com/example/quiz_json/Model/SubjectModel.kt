package com.example.quiz_json.Model

import android.content.Context
import android.widget.Toast
import org.json.JSONObject

class SubjectModel {
    data class subject(
        val Subject : String,
        val image: String
    )

    fun subject(context: Context?): Array<subject> {
        val jsonString = context!!.assets.open("subject.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val subjectJson = json.getJSONArray("Subject")
        val subjectrcy = Array(subjectJson.length()) { i ->
            val questionJson = subjectJson.getJSONObject(i)
            subject(
                questionJson.getString("Subject"),
                questionJson.getString("image")
            )
        }
        Toast.makeText(context, "No.of questions are ${subjectrcy.size}", Toast.LENGTH_SHORT).show()

       return subjectrcy
    }

}