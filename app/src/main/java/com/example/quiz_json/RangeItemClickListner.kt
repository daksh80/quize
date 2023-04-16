package com.example.quiz_json

import com.example.quiz_json.Model.SubjectModel

interface RangeItemClickListner {
    fun rangeIntemClicked(item: SubjectModel.Ranges, position: Int)
}