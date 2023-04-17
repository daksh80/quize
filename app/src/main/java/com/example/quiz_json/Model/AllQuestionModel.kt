package com.example.quiz_json.Model

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.os.persistableBundleOf
import androidx.navigation.navArgument
import com.example.quiz_json.Controllers.RangeStudent
import com.example.quiz_json.Controllers.StartQuizController
import com.example.quiz_json.Difficulty
import com.example.quiz_json.binding
import org.json.JSONObject

class AllQuestionModel() {





    data class McqQuestion(
        val question: String,
        val option1: String,
        val option2: String,
        val option3: String,
        val option4: String,
        val answer: String
    )

//    fun getmcqquestions(context: Context): Array<McqQuestion> {
//
//
//
//        val jsonString = context.assets.open("subject.json").bufferedReader().use { it.readText() }
//        val jsonObject = JSONObject(jsonString)
//        val englishArray = jsonObject.getJSONArray("Subject1").getJSONObject(0).getJSONArray("English")
//        val levelArray = englishArray.getJSONObject(0).getJSONArray("1-3").getJSONObject(0).getJSONArray("Basic")
//
//        val booleanQuestions = Array(levelArray.length()) { i ->
//            val jsonObj = levelArray.getJSONObject(i)
//             McqQuestion(
//                jsonObj.getString("question"),
//                jsonObj.getString("Option1"),
//                jsonObj.getString("Option2"),
//                jsonObj.getString("Option3"),
//                jsonObj.getString("Option4"),
//                jsonObj.getString("correctIndex"))
//        }
//        return  booleanQuestions
//
//
//    }

}






