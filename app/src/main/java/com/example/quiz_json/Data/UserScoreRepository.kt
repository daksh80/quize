// It provides methods for performing CRUD operations on the UserScore entity in the database.
// This class contains a constructor that takes an instance of UserScoreDao as a parameter.

package com.example.quiz_json.Data

import androidx.lifecycle.LiveData

class UserScoreRepository(private val UserScoreDao : UserScoreDao) {

    // This LiveData object holds a list of UserScore objects that is updated every time there is a change in the database.
// It is observed by the ViewModel to update the UI accordingly.
    val readAllData: LiveData<List<UserScore>> = UserScoreDao.readAllData()

    // This method is used to add a new UserScore object to the database.
    suspend fun addUser(userScore : UserScore){
        UserScoreDao.addScore(userScore)
    }

    // This method is used to delete all the UserScore objects from the database.
    suspend fun deleteAllData(){
        UserScoreDao.DeleteAllData()
    }

}