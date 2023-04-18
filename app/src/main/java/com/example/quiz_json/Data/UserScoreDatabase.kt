package com.example.quiz_json.Data

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserScore::class], version = 1, exportSchema = true)
abstract class UserScoreDatabase: RoomDatabase() {
    abstract fun UserScoreDao() : UserScoreDao
    companion object{
        @Volatile
        private var INSTANCE: UserScoreDatabase? = null

        fun getDatabase(context: Context): UserScoreDatabase{
            val tempInstance =  INSTANCE
            if(tempInstance!=null){
                return  tempInstance
            }
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