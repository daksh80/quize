package com.example.quiz_json.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface UserScoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addScore(user : UserScore)

    @Query("SELECT * FROM Score_History ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserScore>>
}