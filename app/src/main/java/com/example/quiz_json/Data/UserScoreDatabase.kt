// Room database for storing user scores
package com.example.quiz_json.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Entity class for user score
@Database(entities = [UserScore::class], version = 1, exportSchema = true)
abstract class UserScoreDatabase: RoomDatabase() {

    // Data Access Object interface for performing database operations
    abstract fun UserScoreDao() : UserScoreDao

    companion object{
        // Singleton instance of the database
        @Volatile
        private var INSTANCE: UserScoreDatabase? = null

        // Returns the database instance, creates one if none exists
        fun getDatabase(context: Context): UserScoreDatabase{
            val tempInstance =  INSTANCE
            if(tempInstance!=null){
                return  tempInstance
            }
            // Synchronized block to prevent multiple threads from creating multiple instances
            synchronized(this){
                val instances = Room.databaseBuilder(
                    context.applicationContext,
                    UserScoreDatabase::class.java,
                    "Score_History"
                ).build()
                INSTANCE = instances
                return instances
            }
        }
    }

}