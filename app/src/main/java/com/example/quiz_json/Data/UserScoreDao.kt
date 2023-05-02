package com.example.quiz_json.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data access object (DAO) interface for the UserScore entity in Room database.
 */
@Dao
interface UserScoreDao {

    /**
     * Adds a new score entry to the database.
     *
     * @param user the UserScore object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addScore(user : UserScore)

    /**
     * Returns a LiveData object containing a list of all score entries in the database.
     *
     * @return a LiveData object containing a list of all score entries in the database.
     */
    @Query("SELECT * FROM Score_History ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserScore>>

    /**
     * Deletes all score entries in the database.
     */
    @Query("DELETE FROM Score_History")
    suspend fun DeleteAllData()
}
