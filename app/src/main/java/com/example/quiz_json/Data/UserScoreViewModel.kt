package com.example.quiz_json.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserScoreViewModel(application: Application): AndroidViewModel(application) {
    // LiveData object that holds the user scores
    val readAllData: LiveData<List<UserScore>>
    private val repository: UserScoreRepository

    init {
        // Instantiate the database and get a reference to the DAO
        val userScoreDao = UserScoreDatabase.getDatabase(application).UserScoreDao()
        // Initialize the repository with the DAO
        repository = UserScoreRepository(userScoreDao)
        // Initialize the LiveData object with the scores from the repository
        readAllData = repository.readAllData
    }

    /**
     * Function to add a user score to the database. This function is called from the UI layer.
     * It launches a new coroutine in the IO context, since the operation involves disk I/O.
     */
    fun addUser(userScore : UserScore){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(userScore)
        }
    }

    /**
     * Function to delete all user scores from the database. This function is called from the UI layer.
     * It launches a new coroutine in the IO context, since the operation involves disk I/O.
     */
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllData()
        }
    }
}
