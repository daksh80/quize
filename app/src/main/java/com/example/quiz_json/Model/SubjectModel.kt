// This is the SubjectModel class, which contains the subject, range, and difficulty data.
package com.example.quiz_json.Model

import android.content.Context
import org.json.JSONObject

class SubjectModel {
    // This data class is used to define the structure of the subject data.
    data class subject(
        val Subject : String,
        val images: String
    )

    // An ArrayList of subject data is initialized.
    val userList: ArrayList<SubjectModel.subject> = ArrayList()

    // This function takes a context as a parameter and reads a JSON file named "AllSubject.json" from the assets folder.
// It then extracts the subject and images data from the JSON file and adds them to the userList ArrayList.
// Finally, it returns the userList ArrayList.
    fun subject(context: Context?): ArrayList<subject> {
        val jsonString = context!!.assets.open("AllSubject.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val subjectJson = json.getJSONArray("Subject")
        for(i in 0 until subjectJson.length()) {
            val questionJson = subjectJson.getJSONObject(i)
            val subjectprimary = subject(
                questionJson.getString("Subject"),
                questionJson.getString("images")
            )
            userList.add(subjectprimary)
        }
        return userList
    }

    // This data class is used to define the structure of the range data.
    data class Ranges(
        val Range: String,
        val images: String
    )

    // An ArrayList of range data is initialized.
    val rangelist: ArrayList<SubjectModel.Ranges> = ArrayList()

    // This function takes a context as a parameter and reads a JSON file named "subject.json" from the assets folder.
// It then extracts the range and images data from the JSON file and adds them to the rangelist ArrayList.
// Finally, it returns the rangelist ArrayList.
    fun Ranges(context: Context?): ArrayList<SubjectModel.Ranges>{
        val jsonString = context!!.assets.open("subject.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val SubRangejson  = json.getJSONArray("Ranges")
        for(i in 0 until SubRangejson.length()){
            val Rangejson = SubRangejson.getJSONObject(i)
            val studRange = Ranges(
                Rangejson.getString("Range"),Rangejson.getString("images")
            )
            rangelist.add(studRange)
        }
        return rangelist
    }

    // This data class is used to define the structure of the difficulty data.
    data class Difficulty(
        val Difficult: String,
        val images: String
    )

    // An ArrayList of difficulty data is initialized.
    val DiffList: ArrayList<SubjectModel.Difficulty> = ArrayList()

    // This function takes a context as a parameter and reads a JSON file named "subject.json" from the assets folder.
// It then extracts the difficulty and images data from the JSON file and adds them to the DiffList ArrayList.
// Finally, it returns the DiffList ArrayList.
    fun Difficulty(context: Context?): ArrayList<SubjectModel.Difficulty>{
        val jsonString = context!!.assets.open("subject.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val Diff = json.getJSONArray("Difficulty")
        for(i in 0 until  Diff.length()){
            val DiffJson = Diff.getJSONObject(i)
            val DiffRange = Difficulty(
                DiffJson.getString("Difficult"),
                DiffJson.getString("images")
            )
            DiffList.add(DiffRange)
        }
        return DiffList
    }

}


