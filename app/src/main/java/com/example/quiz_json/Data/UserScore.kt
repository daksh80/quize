package com.example.quiz_json.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

// This is a data class for storing the user's score history in a local database using Room.
// The @Entity annotation specifies that this class represents a database table, and the tableName attribute specifies the table name.
// Each instance of this class represents a row in the table.
// The @PrimaryKey annotation specifies that the "id" field is the primary key, and auto-generates unique values for each row.
// The remaining fields represent the question, options and the correct answer for the quiz.





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