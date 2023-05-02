package com.example.quiz_json

import com.example.quiz_json.Model.SubjectModel
//The Difficulty function takes two parameters:
//
//item of type SubjectModel.Difficulty: represents the Difficulty item that was clicked by the user.
//position of type Int: represents the position of the clicked item in the list.

interface DifficultyItemClickListner {
   fun Difficulty(item : SubjectModel.Difficulty, position: Int)
}

