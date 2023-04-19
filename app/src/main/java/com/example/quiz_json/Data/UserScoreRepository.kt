package com.example.quiz_json.Data

import androidx.lifecycle.LiveData

class UserScoreRepository(private val UserScoreDao : UserScoreDao) {
    val readAllData: LiveData<List<UserScore>> = UserScoreDao.readAllData()

    suspend fun addUser(userScore : UserScore){
        UserScoreDao.addScore(userScore)
    }


    suspend fun deleteAllData(){
        UserScoreDao.DeleteAllData()
    }
}