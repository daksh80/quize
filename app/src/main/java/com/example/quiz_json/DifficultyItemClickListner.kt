package com.example.quiz_json

import com.example.quiz_json.Model.SubjectModel

interface DifficultyItemClickListner {
   fun Difficulty(item : SubjectModel.Difficulty, position: Int)
}