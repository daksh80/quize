package com.example.quiz_json.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserScoreViewModel(application: Application): AndroidViewModel(application) {
      val readAllData: LiveData<List<UserScore>>
    private val repository: UserScoreRepository

    init {
        val UserScoreDao = UserScoreDatabase.getDatabase(application).UserScoreDao()
        repository = UserScoreRepository(UserScoreDao)
        readAllData = repository.readAllData
    }
    fun addUser(userScore : UserScore){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(userScore)
        }
    }
}