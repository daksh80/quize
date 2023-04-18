package com.example.quiz_json.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Score_History")
data class UserScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String
)